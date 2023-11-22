package ru.sonya.week3.model

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.sonya.week3.model.retrofit2.RetrofitCommon
import ru.sonya.week3.model.roomDB.AppDatabase
import ru.sonya.week3.model.roomDB.RoomCat
import java.util.Date

val refresh = Date()

class CatsRepository(
    private val db: AppDatabase,
    private val sharedPreferences: SharedPreferences
) {

    val catDao = db.catsDao()
    suspend fun updateItems(): Result<Unit> {

        val cashTime = 3600000
        val time = Date()
        val refreshTime = Date(sharedPreferences.getLong("refresh_time", 0))

        return try {
            Result.success(
                if (time.time - refreshTime.time > cashTime) {

                    catDao.deleteAllCats()

                    val mService = RetrofitCommon.retrofitService
                    val body = mService.getCats().body()

                  /*  catDao.insertAll(
                        flow {
                            emit(
                                body?.map {
                                    RoomCat(
                                        url = it.url,
                                        name = it.breeds[0].name,
                                        description = it.breeds[0].description,
                                        id = 0
                                    )
                                }.orEmpty()
                            )
                        }
                    )
                    если делать в инсертолл тип флоу билдер ругается(
                    */

                        catDao.insertAll( body!!.map { RoomCat(
                            url = it.url,
                            name = it.breeds[0].name,
                            description = it.breeds[0].description,
                            id = 0
                        ) } )


                    sharedPreferences.edit {
                        putLong("refresh_time", refresh.time)
                    }

                } else {
                    // так а тут что, без елс ругаются
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    fun getItemsFlow(): Flow<List<RoomCat>> {
        return catDao.getAllCats()
    }

}


