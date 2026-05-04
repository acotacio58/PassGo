package co.edu.uan.android.passgo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "credentials",
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
data class CredentialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    @ColumnInfo(name = "site_name")
    val siteName: String,
    @ColumnInfo(name = "site_url")
    val siteUrl: String? = null,
    @ColumnInfo(name = "site_username")
    val siteUsername: String,
    @ColumnInfo(name = "site_password")
    val sitePassword: String,
    val category: String, // Nueva campo: Redes Sociales, Aplicaciones, Cartera
    val notes: String? = null,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
