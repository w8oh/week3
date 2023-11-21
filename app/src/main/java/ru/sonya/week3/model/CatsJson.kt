package ru.sonya.week3.model
import com.google.gson.annotations.SerializedName

data class CatJson(
    @SerializedName("breeds") var breeds: List<Breeds> = listOf(),
    @SerializedName("url") var url: String? = null
)