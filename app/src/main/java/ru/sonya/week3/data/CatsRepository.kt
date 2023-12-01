package ru.sonya.week3.data

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.retrofit2.CatJson
import ru.sonya.week3.data.retrofit2.RetrofitCommon
import ru.sonya.week3.data.roomDB.AppDatabase
import ru.sonya.week3.data.roomDB.RoomCat
import ru.sonya.week3.domain.Repository
import java.util.Date
import javax.inject.Inject

val refresh = Date()

class CatsRepository (
    private val db: AppDatabase,
    private val sharedPreferences: SharedPreferences
): Repository {

    private val catDao = db.catsDao()

    override suspend fun updateItems(): Result<Unit> {

        val cashTime = 3600000
        val time = Date()
        val refreshTime = Date(sharedPreferences.getLong("refresh_time", 0))

        return try {
            Result.success(
                if (time.time - refreshTime.time > cashTime) {

                    catDao.deleteAllCats()

                    val body: List<CatJson>? = RetrofitCommon.retrofitService.getCats().body()

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
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override fun getItemsFlow(): Flow<List<RoomCat>> {
        return catDao.getAllCats()
    }

}


