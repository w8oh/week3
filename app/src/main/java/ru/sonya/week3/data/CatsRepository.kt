package ru.sonya.week3.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.retrofit2.CatJson
import ru.sonya.week3.data.retrofit2.RetrofitCommon
import ru.sonya.week3.data.retrofit2.RetrofitServices
import ru.sonya.week3.data.roomDB.CatsDAO
import ru.sonya.week3.data.roomDB.RoomCat
import java.util.Date
import javax.inject.Inject

val refresh = Date()

class CatsRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val api: RetrofitServices,
    private val dao: CatsDAO
) : Repository {

    override suspend fun updateItems(): Result<Unit> {

        val cacheTime = 3600000
        val time = Date()
        val refreshTime = Date(sharedPreferences.getLong("refresh_time", 0))

        return try {
            Result.success(
                if (time.time - refreshTime.time > cacheTime) {

                    dao.deleteAllCats()

                    val body: List<CatJson>? = api.getCats().body()

                    dao.insertAll(body!!.map {
                        RoomCat(
                            url = it.url,
                            name = it.breeds[0].name,
                            description = it.breeds[0].description,
                            id = 0
                        )
                    })


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
        return dao.getAllCats()
    }

}


