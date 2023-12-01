package ru.sonya.week3.domain

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.roomDB.AppDatabase
import ru.sonya.week3.data.roomDB.DBApp
import ru.sonya.week3.data.roomDB.RoomCat
import java.util.Date

interface Repository {

    fun getItemsFlow(): Flow<List<RoomCat>>
    suspend fun updateItems(): Result<Unit>
}