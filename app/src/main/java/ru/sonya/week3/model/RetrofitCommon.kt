package ru.sonya.week3.model

object RetrofitCommon {
    private val BASE_URL = "https://api.thecatapi.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}