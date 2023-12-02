package ru.sonya.week3.domain

import kotlinx.coroutines.flow.Flow
import ru.sonya.week3.data.roomDB.RoomCat

class GetCatsUseCase(
    private val repository: Repository
) : GetUseCase {

    override operator fun invoke(): Flow<List<FunCat>> {
        return repository.getItemsFlow()
    }
}