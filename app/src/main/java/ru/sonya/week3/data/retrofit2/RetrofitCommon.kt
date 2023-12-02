package ru.sonya.week3.data.retrofit2

class RetrofitCommon {
    private val BASE_URL = "https://api.thecatapi.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}