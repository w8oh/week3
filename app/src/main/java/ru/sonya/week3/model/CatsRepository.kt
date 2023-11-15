package ru.sonya.week3.model

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response
import java.io.Console
import java.io.IOException





class CatsRepository {

    lateinit var mService: RetrofitServices
    lateinit var catList: List<CatJson>
    fun getCats(): List<CatJson>? {
        mService = RetrofitCommon.retrofitService
        catList = listOf()

        mService.getCats().enqueue(object : Callback<List<CatJson>> {
            override fun onFailure(call: Call<List<CatJson>>, t: Throwable) {/**/
            }

            override fun onResponse(call: Call<List<CatJson>>, response: Response<List<CatJson>>) {
                catList = response.body() as List<CatJson>
            }
        })

        return catList

    }
}


