package ru.sonya.week3.model

import android.content.SharedPreferences

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
        var oldTime: Long = 0
        var newTime: Long = 0

        oldTime = sharedPreferences.getLong("old_time", oldTime)
        newTime = sharedPreferences.getLong("time", newTime)

        if ((newTime-oldTime)>3600000)
        {
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
        }

        val roomCats: List<RoomCat>? = catDao.getAllCats()

        return try {
            Result.success(roomCats)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}


