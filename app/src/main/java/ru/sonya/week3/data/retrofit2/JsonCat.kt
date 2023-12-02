package ru.sonya.week3.data.retrofit2
import com.google.gson.annotations.SerializedName
import ru.sonya.week3.data.roomDB.RoomCat
import ru.sonya.week3.ui.view.ItemCat
import ru.sonya.week3.ui.viewModel.DtoCat

data class CatJson(
    @SerializedName("breeds") var breeds: List<Breeds> = listOf(),
    @SerializedName("url") var url: String? = null
)

fun CatJson.mapToRoom() =
    RoomCat(
        url = url,
        name = breeds[0].name,
        description = breeds[0].description,
        id = 0
    )
