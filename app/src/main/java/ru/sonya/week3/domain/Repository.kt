package ru.sonya.week3.domain

import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.roomDB.RoomCat

interface Repository {

    fun getItemsFlow(): Flow<List<FunCat>>
    suspend fun updateItems(): Result<Unit>
}