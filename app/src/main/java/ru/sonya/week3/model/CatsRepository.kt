package ru.sonya.week3.model

import android.content.SharedPreferences
import java.util.Date

class CatsRepository(
    private val db: AppDatabase,
    private val sharedPreferences: SharedPreferences
) {

    suspend fun getCats(): Result<List<RoomCat>?> {

        val mService = RetrofitCommon.retrofitService
        val response = mService.getCats()
        val body = response.body()
        val catDao = db.catsDao()
        val hour = 3600000
        var refresh: Date

        var time = sharedPreferences.getLong("time", 0)
        var refreshTime = sharedPreferences.getLong("refresh_time", 0)


        if ( time - refreshTime > hour) {
            catDao.deleteAllCats()
            body?.map {
                catDao.insert(
                    RoomCat(
                        url = it.url,
                        name = it.breeds[0].name,
                        description = it.breeds[0].description,
                        id = 0
                    )
                )
            }
            refresh = Date(System.currentTimeMillis())
            sharedPreferences.edit().putLong("refresh_time", refresh.getTime()).commit()
        }

        val roomCats: List<RoomCat>? = catDao.getAllCats()

        return try {
            Result.success(roomCats)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}


