package ru.sonya.week3.model
import com.google.gson.annotations.SerializedName

data class Breeds (

    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("description" ) var description : String? = null

)