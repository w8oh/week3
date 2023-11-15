package ru.sonya.week3.model

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response
import java.io.IOException

class CatsRepository {

    var mService: RetrofitServices = RetrofitCommon.retrofitService
    var catList: List<CatJson>? = listOf()

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    fun getCats() = runBlocking {
        mService = RetrofitCommon.retrofitService
        catList = listOf()
        catList = mService.getCats().body()
    }
}


