package co.edu.uan.android.passgo.data.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.passgo.data.local.entity.CredentialEntity
import co.edu.uan.android.passgo.data.local.entity.UserEntity
import co.edu.uan.android.passgo.data.repository.PassGoRepositoryProvider
import kotlinx.coroutines.launch

class PassGoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PassGoRepositoryProvider.getRepository(application)

    var currentUser by mutableStateOf<UserEntity?>(null)
        private set

    var authMessage by mutableStateOf<String?>(null)
        private set

    var categoryCounts by mutableStateOf<Map<String, Int>>(emptyMap())
        private set

    var credentials by mutableStateOf<List<CredentialEntity>>(emptyList())
        private set

    var favoriteCredentials by mutableStateOf<List<CredentialEntity>>(emptyList())
        private set

    var generatedPassword by mutableStateOf("")
        private set

    var recoveryMessage by mutableStateOf<String?>(null)
        private set

    var recoveryCode by mutableStateOf<String?>(null)
        private set

    var verificationCode by mutableStateOf<String?>(null)
        private set

    var verificationMessage by mutableStateOf<String?>(null)
        private set

    var profileMessage by mutableStateOf<String?>(null)
        private set

    var recoveryUserId by mutableStateOf<Long?>(null)
        private set

    fun showRecoveryResendMessage() {
        recoveryMessage = "Se reenviará el código al correo registrado."
    }

    fun login(usernameOrEmail: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            authMessage = null
            val result = repository.authenticate(usernameOrEmail.trim(), password)
            if (result.isSuccess) {
                currentUser = result.getOrNull()
                loadCredentials()
                onSuccess()
            } else {
                authMessage = result.exceptionOrNull()?.message ?: "Error de autenticación."
            }
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            authMessage = null
            val username = "${firstName.trim()}_${lastName.trim()}".lowercase()
            val result = repository.registerUser(username, email.trim(), password)
            if (result.isSuccess) {
                currentUser = result.getOrNull()
                verificationCode = result.getOrNull()?.verificationCode
                verificationMessage = null
                loadCredentials()
                onSuccess()
            } else {
                authMessage = result.exceptionOrNull()?.message ?: "Error de registro."
            }
        }
    }

    fun requestPasswordRecovery(email: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            recoveryMessage = null
            val result = repository.requestPasswordRecovery(email.trim())
            if (result.isSuccess) {
                recoveryCode = result.getOrNull()?.recoveryCode
                recoveryUserId = result.getOrNull()?.userId
                recoveryMessage = "Código de recuperación creado. Utiliza: ${recoveryCode ?: "----"}"
                onComplete()
            } else {
                recoveryMessage = result.exceptionOrNull()?.message ?: "No se pudo generar la recuperación."
            }
        }
    }

    fun resetPassword(code: String, newPassword: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            recoveryMessage = null
            val userId = recoveryUserId
            if (userId == null) {
                recoveryMessage = "Debes solicitar la recuperación antes de restablecer la contraseña."
                return@launch
            }
            val result = repository.resetPassword(userId, code.trim(), newPassword)
            if (result.isSuccess) {
                recoveryMessage = "Contraseña restablecida correctamente."
                recoveryUserId = null
                recoveryCode = null
                onSuccess()
            } else {
                recoveryMessage = result.exceptionOrNull()?.message ?: "No se pudo restablecer la contraseña."
            }
        }
    }

    fun verifyEmailCode(code: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            verificationMessage = null
            val user = currentUser
            if (user == null) {
                verificationMessage = "No hay usuario registrado para verificar."
                return@launch
            }
            val result = repository.verifyEmailCode(user.id, code.trim())
            if (result.isSuccess) {
                currentUser = result.getOrNull()
                verificationCode = null
                verificationMessage = "Correo verificado correctamente."
                onSuccess()
            } else {
                verificationMessage = result.exceptionOrNull()?.message ?: "Código incorrecto."
            }
        }
    }

    fun resendVerificationCode(onComplete: () -> Unit) {
        viewModelScope.launch {
            verificationMessage = null
            val user = currentUser
            if (user == null) {
                verificationMessage = "No hay usuario registrado para reenviar el código."
                return@launch
            }
            val result = repository.resendVerificationCode(user.id)
            if (result.isSuccess) {
                currentUser = result.getOrNull()
                verificationCode = result.getOrNull()?.verificationCode
                verificationMessage = "Se ha reenviado el código de verificación."
                onComplete()
            } else {
                verificationMessage = result.exceptionOrNull()?.message ?: "No se pudo reenviar el código."
            }
        }
    }

    fun updateProfileImage(uriString: String) {
        viewModelScope.launch {
            profileMessage = null
            val user = currentUser
            if (user == null) {
                profileMessage = "No hay usuario activo."
                return@launch
            }
            val result = repository.updateUserProfileImage(user.id, uriString)
            if (result.isSuccess) {
                currentUser = result.getOrNull()
                profileMessage = "Foto de perfil actualizada."
            } else {
                profileMessage = result.exceptionOrNull()?.message ?: "No se pudo guardar la foto." 
            }
        }
    }

    fun generatePassword(
        length: Int,
        uppercase: Boolean,
        lowercase: Boolean,
        digits: Boolean,
        symbols: Boolean
    ) {
        generatedPassword = buildString {
            val upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            val lowerChars = "abcdefghijklmnopqrstuvwxyz"
            val numberChars = "0123456789"
            val symbolChars = "!@#%&*()-_=+[]{}|;:,.<>?"
            val pool = StringBuilder().apply {
                if (uppercase) append(upperChars)
                if (lowercase) append(lowerChars)
                if (digits) append(numberChars)
                if (symbols) append(symbolChars)
            }.toString()

            if (pool.isEmpty()) {
                append("Selecciona al menos un tipo de carácter.")
                return
            }

            repeat(length.coerceIn(4, 64)) {
                append(pool.random())
            }
        }
    }

    fun saveGeneratedPassword(siteName: String, siteUrl: String?, siteUsername: String, category: String, note: String?) {
        val user = currentUser ?: return
        viewModelScope.launch {
            val password = if (generatedPassword.isNotBlank()) generatedPassword else ""
            repository.addCredential(user.id, siteName, siteUrl, siteUsername, password, category, note)
            loadCredentials()
            profileMessage = "Contraseña guardada correctamente."
        }
    }

    fun addCredential(siteName: String, siteUrl: String?, siteUsername: String, sitePassword: String, category: String, notes: String?) {
        val user = currentUser ?: return
        viewModelScope.launch {
            val result = repository.addCredential(user.id, siteName, siteUrl, siteUsername, sitePassword, category, notes)
            if (result.isSuccess) {
                loadCredentials()
                profileMessage = "Credencial agregada correctamente."
            } else {
                profileMessage = result.exceptionOrNull()?.message ?: "Error al agregar credencial."
            }
        }
    }

    fun updateCredential(credential: CredentialEntity) {
        viewModelScope.launch {
            val result = repository.updateCredential(credential)
            if (result.isSuccess) {
                loadCredentials()
                profileMessage = "Credencial actualizada correctamente."
            } else {
                profileMessage = result.exceptionOrNull()?.message ?: "Error al actualizar credencial."
            }
        }
    }

    fun toggleFavorite(credential: CredentialEntity) {
        viewModelScope.launch {
            val result = repository.toggleFavorite(credential)
            if (result.isSuccess) {
                loadCredentials()
                profileMessage = if (result.getOrNull()?.isFavorite == true) "Agregado a favoritos" else "Removido de favoritos"
            } else {
                profileMessage = result.exceptionOrNull()?.message ?: "Error al actualizar favorito."
            }
        }
    }

    fun deleteCredential(credential: CredentialEntity) {
        viewModelScope.launch {
            repository.deleteCredential(credential)
            loadCredentials()
            profileMessage = "Credencial eliminada correctamente."
        }
    }

    suspend fun getCredentialCountByCategory(category: String): Int {
        val user = currentUser ?: return 0
        return repository.getCredentialCountByCategory(user.id, category)
    }

    fun loadCredentials() {
        val user = currentUser ?: return
        viewModelScope.launch {
            credentials = repository.getCredentials(user.id)
            favoriteCredentials = repository.getFavoriteCredentials(user.id)
            categoryCounts = mapOf(
                "Redes Sociales" to repository.getCredentialCountByCategory(user.id, "Redes Sociales"),
                "Aplicaciones" to repository.getCredentialCountByCategory(user.id, "Aplicaciones"),
                "Cartera" to repository.getCredentialCountByCategory(user.id, "Cartera")
            )
        }
    }

    fun logout(onComplete: () -> Unit) {
        currentUser = null
        credentials = emptyList()
        favoriteCredentials = emptyList()
        authMessage = null
        profileMessage = null
        generatedPassword = ""
        recoveryCode = null
        onComplete()
    }

    fun deleteAccount(onComplete: () -> Unit, onError: (String) -> Unit) {
        profileMessage = null
        val user = currentUser
        if (user == null) {
            onError("No hay usuario activo.")
            return
        }
        viewModelScope.launch {
            try {
                repository.deleteUser(user)
                logout(onComplete)
            } catch (cause: Throwable) {
                onError(cause.message ?: "No se pudo eliminar la cuenta.")
            }
        }
    }
}
