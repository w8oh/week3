package ru.sonya.week3.data

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.retrofit2.CatJson
import ru.sonya.week3.data.retrofit2.RetrofitServices
import ru.sonya.week3.data.roomDB.CatsDAO
import ru.sonya.week3.data.roomDB.RoomCat
import ru.sonya.week3.domain.FunCat
import ru.sonya.week3.domain.Repository
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import ru.sonya.week3.data.retrofit2.mapToRoom
import ru.sonya.week3.data.roomDB.mapToDomain

class CatsRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val api: RetrofitServices,
    private val dao: CatsDAO
) : Repository {

    override suspend fun updateItems(): Result<Unit> {

        val refresh = Date()
        val cacheTime = 3600000
        val time = Date()
        val refreshTime = Date(sharedPreferences.getLong("refresh_time", 0))

        return runCatching {
            Result.success(
                if (time.time - refreshTime.time > cacheTime) {

                    dao.deleteAllCats()

                    val body: List<CatJson>? = api.getCats().body()

                    dao.insertAll(body!!.map { it.mapToRoom() })

                    sharedPreferences.edit {
                        putLong("refresh_time", refresh.time)
                    }

                } else {
                }
            ).onFailure {
                Log.w("CatsRepository", "Cat-Getting is failured.")
            }
        }

    }

    override fun getItemsFlow(): Flow<List<FunCat>> {
        return dao.getAllCats().map { it.map { it.mapToDomain() } }
    }

}


