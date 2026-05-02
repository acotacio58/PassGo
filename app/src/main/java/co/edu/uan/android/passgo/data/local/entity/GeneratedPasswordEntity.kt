package co.edu.uan.android.passgo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "generated_passwords",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["user_id"])
    ]
)
data class GeneratedPasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    val password: String,
    val length: Int? = null,
    @ColumnInfo(name = "has_uppercase")
    val hasUppercase: Boolean = false,
    @ColumnInfo(name = "has_numbers")
    val hasNumbers: Boolean = false,
    @ColumnInfo(name = "has_symbols")
    val hasSymbols: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
