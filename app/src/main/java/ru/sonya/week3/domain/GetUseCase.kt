package ru.sonya.week3.domain

import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.roomDB.RoomCat

interface GetUseCase {
    operator fun invoke(): Flow<List<RoomCat>>
}