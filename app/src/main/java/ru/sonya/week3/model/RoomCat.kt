package ru.sonya.week3.model
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "DBCats")
data class RoomCat(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "description") var description: String? = null
)