package co.edu.uan.android.passgo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.uan.android.passgo.data.local.dao.CredentialDao
import co.edu.uan.android.passgo.data.local.dao.GeneratedPasswordDao
import co.edu.uan.android.passgo.data.local.dao.PasswordRecoveryDao
import co.edu.uan.android.passgo.data.local.dao.UserDao
import co.edu.uan.android.passgo.data.local.entity.CredentialEntity
import co.edu.uan.android.passgo.data.local.entity.GeneratedPasswordEntity
import co.edu.uan.android.passgo.data.local.entity.PasswordRecoveryEntity
import co.edu.uan.android.passgo.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        CredentialEntity::class,
        GeneratedPasswordEntity::class,
        PasswordRecoveryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun credentialDao(): CredentialDao
    abstract fun generatedPasswordDao(): GeneratedPasswordDao
    abstract fun passwordRecoveryDao(): PasswordRecoveryDao
}
