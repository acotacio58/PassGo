package co.edu.uan.android.passgo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.edu.uan.android.passgo.data.local.entity.PasswordRecoveryEntity

@Dao
interface PasswordRecoveryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(request: PasswordRecoveryEntity): Long

    @Update
    suspend fun update(request: PasswordRecoveryEntity)

    @Delete
    suspend fun delete(request: PasswordRecoveryEntity)

    @Query("SELECT * FROM password_recovery WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): PasswordRecoveryEntity?

    @Query("SELECT * FROM password_recovery WHERE user_id = :userId AND recovery_code = :code LIMIT 1")
    suspend fun getByCode(userId: Long, code: String): PasswordRecoveryEntity?
}
