package ru.sonya.week3.data.roomDB
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sonya.week3.domain.FunCat
import ru.sonya.week3.ui.view.ItemCat
import ru.sonya.week3.ui.viewModel.DtoCat
import java.util.UUID

@Entity(tableName = "DBCats")
data class RoomCat(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "description") var description: String? = null
)

fun RoomCat.mapToDomain() =
    FunCat(
        image = url,
        title = name,
        subtitle = description
    )
