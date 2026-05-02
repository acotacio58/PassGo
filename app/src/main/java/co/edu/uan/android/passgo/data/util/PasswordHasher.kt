package co.edu.uan.android.passgo.data.util

import java.security.MessageDigest

object PasswordHasher {
    fun hash(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest(password.encodeToByteArray())
        return bytes.joinToString(separator = "") { "%02x".
            format(it)
        }
    }
}
