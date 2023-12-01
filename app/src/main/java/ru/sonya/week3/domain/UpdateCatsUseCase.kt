package ru.sonya.week3.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateCatsUseCase(
    repository: Repository
) {
    val _repository = repository
    suspend operator fun invoke(): Result<Unit> {
        return _repository.updateItems()
    }

}