package ru.sonya.week3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "DBCats")
data class CatJson(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("breeds") var breeds: List<Breeds> = listOf(),
    @SerializedName("url") var url: String? = null
)