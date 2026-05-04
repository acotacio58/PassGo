package co.edu.uan.android.passgo.data.repository

import android.content.Context
import co.edu.uan.android.passgo.data.local.AppDatabase
import co.edu.uan.android.passgo.data.local.DatabaseProvider
import co.edu.uan.android.passgo.data.local.entity.CredentialEntity
import co.edu.uan.android.passgo.data.local.entity.GeneratedPasswordEntity
import co.edu.uan.android.passgo.data.local.entity.PasswordRecoveryEntity
import co.edu.uan.android.passgo.data.local.entity.UserEntity
import co.edu.uan.android.passgo.data.util.PasswordHasher
import kotlin.random.Random

class PassGoRepository(private val database: AppDatabase) {

    private val userDao = database.userDao()
    private val credentialDao = database.credentialDao()
    private val generatedPasswordDao = database.generatedPasswordDao()
    private val passwordRecoveryDao = database.passwordRecoveryDao()

    suspend fun registerUser(username: String, email: String, password: String): Result<UserEntity> {
        if (userDao.findByUsername(username) != null) {
            return Result.failure(IllegalArgumentException("El nombre de usuario ya está en uso."))
        }
        if (userDao.findByEmail(email) != null) {
            return Result.failure(IllegalArgumentException("El correo electrónico ya está registrado."))
        }

        val passwordHash = PasswordHasher.hash(password)
        val verificationCode = generateRecoveryCode()
        val userId = userDao.insert(
            UserEntity(
                username = username,
                email = email,
                passwordHash = passwordHash,
                verificationCode = verificationCode,
                isEmailVerified = false
            )
        )
        return userDao.getById(userId)?.let { Result.success(it) }
            ?: Result.failure(IllegalStateException("No se pudo crear el usuario."))
    }

    suspend fun authenticate(usernameOrEmail: String, password: String): Result<UserEntity> {
        val passwordHash = PasswordHasher.hash(password)
        val user = userDao.authenticate(usernameOrEmail, passwordHash)
        return if (user != null) {
            Result.success(user)
        } else {
            Result.failure(IllegalArgumentException("Usuario o contraseña incorrectos."))
        }
    }

    suspend fun requestPasswordRecovery(email: String): Result<PasswordRecoveryEntity> {
        val user = userDao.findByEmail(email)
            ?: return Result.failure(IllegalArgumentException("No existe un usuario con ese correo electrónico."))

        val recoveryCode = generateRecoveryCode()
        val expiration = System.currentTimeMillis() + 3600_000L
        val request = PasswordRecoveryEntity(
            userId = user.id,
            recoveryCode = recoveryCode,
            expirationTime = expiration
        )
        val id = passwordRecoveryDao.insert(request)
        return passwordRecoveryDao.getById(id)?.let { Result.success(it) }
            ?: Result.failure(IllegalStateException("No se pudo crear la solicitud de recuperación."))
    }

    suspend fun verifyRecoveryCode(userId: Long, code: String): Boolean {
        val request = passwordRecoveryDao.getByCode(userId, code) ?: return false
        if (request.used) return false
        if (request.expirationTime != null && request.expirationTime < System.currentTimeMillis()) {
            return false
        }
        return true
    }

    suspend fun verifyEmailCode(userId: Long, code: String): Result<UserEntity> {
        val user = userDao.getById(userId)
            ?: return Result.failure(IllegalArgumentException("Usuario no encontrado."))
        if (user.verificationCode.isNullOrBlank() || user.verificationCode != code) {
            return Result.failure(IllegalArgumentException("Código de verificación incorrecto."))
        }
        val updatedUser = user.copy(isEmailVerified = true, verificationCode = null)
        userDao.update(updatedUser)
        return Result.success(updatedUser)
    }

    suspend fun resendVerificationCode(userId: Long): Result<UserEntity> {
        val user = userDao.getById(userId)
            ?: return Result.failure(IllegalArgumentException("Usuario no encontrado."))
        val newCode = generateRecoveryCode()
        val updatedUser = user.copy(verificationCode = newCode)
        userDao.update(updatedUser)
        return Result.success(updatedUser)
    }

    suspend fun updateUserProfileImage(userId: Long, uri: String): Result<UserEntity> {
        val user = userDao.getById(userId)
            ?: return Result.failure(IllegalArgumentException("Usuario no encontrado."))
        val updatedUser = user.copy(profileImageUri = uri)
        userDao.update(updatedUser)
        return Result.success(updatedUser)
    }

    suspend fun resetPassword(userId: Long, code: String, newPassword: String): Result<UserEntity> {
        val request = passwordRecoveryDao.getByCode(userId, code)
            ?: return Result.failure(IllegalArgumentException("Código de recuperación incorrecto."))

        if (request.used) {
            return Result.failure(IllegalArgumentException("El código ya fue usado."))
        }
        if (request.expirationTime != null && request.expirationTime < System.currentTimeMillis()) {
            return Result.failure(IllegalArgumentException("El código ha expirado."))
        }

        val user = userDao.getById(userId)
            ?: return Result.failure(IllegalArgumentException("Usuario no encontrado."))

        userDao.update(user.copy(passwordHash = PasswordHasher.hash(newPassword)))
        passwordRecoveryDao.update(request.copy(used = true))

        return Result.success(user.copy(passwordHash = PasswordHasher.hash(newPassword)))
    }

    suspend fun addCredential(
        userId: Long,
        siteName: String,
        siteUrl: String?,
        siteUsername: String,
        sitePassword: String,
        category: String,
        notes: String?
    ): Result<CredentialEntity> {
        val id = credentialDao.insert(
            CredentialEntity(
                userId = userId,
                siteName = siteName,
                siteUrl = siteUrl,
                siteUsername = siteUsername,
                sitePassword = sitePassword,
                category = category,
                notes = notes
            )
        )
        return credentialDao.getById(userId, id)?.let { Result.success(it) }
            ?: Result.failure(IllegalStateException("No se pudo guardar la credencial."))
    }

    suspend fun getCredentials(userId: Long): List<CredentialEntity> {
        return credentialDao.getAllByUser(userId)
    }

    suspend fun searchCredentials(userId: Long, query: String): List<CredentialEntity> {
        return credentialDao.search(userId, query)
    }

    suspend fun updateCredential(credential: CredentialEntity): Result<CredentialEntity> {
        credentialDao.update(credential)
        return Result.success(credential)
    }

    suspend fun toggleFavorite(credential: CredentialEntity): Result<CredentialEntity> {
        val updated = credential.copy(isFavorite = !credential.isFavorite)
        credentialDao.update(updated)
        return Result.success(updated)
    }

    suspend fun getCredentialsByCategory(userId: Long, category: String): List<CredentialEntity> {
        return credentialDao.getByCategory(userId, category)
    }

    suspend fun getCredentialCountByCategory(userId: Long, category: String): Int {
        return credentialDao.getCountByCategory(userId, category)
    }

    suspend fun getFavoriteCredentials(userId: Long): List<CredentialEntity> {
        return credentialDao.getFavorites(userId)
    }

    suspend fun getFavoriteCount(userId: Long): Int {
        return credentialDao.getFavoriteCount(userId)
    }

    suspend fun deleteCredential(credential: CredentialEntity) {
        credentialDao.delete(credential)
    }

    suspend fun addGeneratedPassword(
        userId: Long,
        password: String,
        length: Int,
        hasUppercase: Boolean,
        hasNumbers: Boolean,
        hasSymbols: Boolean
    ): Result<GeneratedPasswordEntity> {
        val id = generatedPasswordDao.insert(
            GeneratedPasswordEntity(
                userId = userId,
                password = password,
                length = length,
                hasUppercase = hasUppercase,
                hasNumbers = hasNumbers,
                hasSymbols = hasSymbols
            )
        )
        return generatedPasswordDao.getAllByUser(userId).firstOrNull { it.id == id }?.let { Result.success(it) }
            ?: Result.failure(IllegalStateException("No se pudo guardar la contraseña generada."))
    }

    suspend fun getGeneratedPasswords(userId: Long): List<GeneratedPasswordEntity> {
        return generatedPasswordDao.getAllByUser(userId)
    }

    suspend fun getUserProfile(userId: Long): UserEntity? {
        return userDao.getById(userId)
    }

    suspend fun deleteUser(user: UserEntity) {
        userDao.delete(user)
    }

    private fun generateRecoveryCode(): String {
        return Random.nextInt(100000, 999999).toString()
    }
}

object PassGoRepositoryProvider {
    fun getRepository(context: Context): PassGoRepository {
        return PassGoRepository(DatabaseProvider.getDatabase(context))
    }
}
