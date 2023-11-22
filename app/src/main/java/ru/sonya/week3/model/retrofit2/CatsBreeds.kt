package ru.sonya.week3.model.retrofit2
import com.google.gson.annotations.SerializedName

data class Breeds (

    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("description" ) var description : String? = null

)