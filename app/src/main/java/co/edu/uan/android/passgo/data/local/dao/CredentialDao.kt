package co.edu.uan.android.passgo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.edu.uan.android.passgo.data.local.entity.CredentialEntity

@Dao
interface CredentialDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(credential: CredentialEntity): Long

    @Update
    suspend fun update(credential: CredentialEntity)

    @Delete
    suspend fun delete(credential: CredentialEntity)

    @Query("SELECT * FROM credentials WHERE user_id = :userId ORDER BY created_at DESC")
    suspend fun getAllByUser(userId: Long): List<CredentialEntity>

    @Query("SELECT * FROM credentials WHERE user_id = :userId AND id = :credentialId LIMIT 1")
    suspend fun getById(userId: Long, credentialId: Long): CredentialEntity?

    @Query("SELECT * FROM credentials WHERE user_id = :userId AND site_name LIKE '%' || :query || '%' ORDER BY created_at DESC")
    suspend fun search(userId: Long, query: String): List<CredentialEntity>
}
