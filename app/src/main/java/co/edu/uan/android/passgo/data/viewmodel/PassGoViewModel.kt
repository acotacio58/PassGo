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

fun showRecoveryResendMessage() {
    recoveryMessage = "Se reenviará el código al correo registrado."
}

class PassGoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PassGoRepositoryProvider.getRepository(application)

    var currentUser by mutableStateOf<UserEntity?>(null)
        private set

    var authMessage by mutableStateOf<String?>(null)
        private set

    var credentials by mutableStateOf<List<CredentialEntity>>(emptyList())
        private set

    var generatedPassword by mutableStateOf("")
        private set

    var recoveryMessage by mutableStateOf<String?>(null)
        private set

    var recoveryCode by mutableStateOf<String?>(null)
        private set

    var recoveryUserId by mutableStateOf<Long?>(null)
        private set

    var profileMessage by mutableStateOf<String?>(null)
        private set

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

    fun saveGeneratedPassword(siteName: String, siteUrl: String?, siteUsername: String, note: String?) {
        val user = currentUser ?: return
        viewModelScope.launch {
            val password = if (generatedPassword.isNotBlank()) generatedPassword else ""
            repository.addCredential(user.id, siteName, siteUrl, siteUsername, password, note)
            loadCredentials()
            profileMessage = "Contraseña guardada correctamente."
        }
    }

    fun loadCredentials() {
        val user = currentUser ?: return
        viewModelScope.launch {
            credentials = repository.getCredentials(user.id)
        }
    }

    fun logout(onComplete: () -> Unit) {
        currentUser = null
        credentials = emptyList()
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
