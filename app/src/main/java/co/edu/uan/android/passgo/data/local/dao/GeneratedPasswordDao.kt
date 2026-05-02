package co.edu.uan.android.passgo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.edu.uan.android.passgo.data.local.entity.GeneratedPasswordEntity

@Dao
interface GeneratedPasswordDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entry: GeneratedPasswordEntity): Long

    @Update
    suspend fun update(entry: GeneratedPasswordEntity)

    @Delete
    suspend fun delete(entry: GeneratedPasswordEntity)

    @Query("SELECT * FROM generated_passwords WHERE user_id = :userId ORDER BY created_at DESC")
    suspend fun getAllByUser(userId: Long): List<GeneratedPasswordEntity>
}
