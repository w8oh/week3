package ru.sonya.week3.data

import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.roomDB.RoomCat

interface Repository {

    fun getItemsFlow(): Flow<List<RoomCat>>
    suspend fun updateItems(): Result<Unit>
}