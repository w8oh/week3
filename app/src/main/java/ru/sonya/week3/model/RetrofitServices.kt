package ru.sonya.week3.model

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitServices {
    @GET("v1/images/search?limit=10&has_breeds=1&api_key=live_1RcSPIGEJmHTdHKk3P2utwyf0sHwAjBIDuKQUrRt6QV1icGzrrMHDUraB7ArUwRX")
    suspend fun getCats(): Response<List<CatJson>>
}
