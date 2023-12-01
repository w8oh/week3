package ru.sonya.week3.domain

import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.roomDB.RoomCat

class GetCatsUseCase(repository: Repository) {

    private val getItemsFlow = repository.getItemsFlow()

    operator fun invoke(): Flow<List<RoomCat>> {
        return getItemsFlow
    }
}