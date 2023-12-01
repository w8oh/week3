package ru.sonya.week3.domain

import ru.sonya.week3.data.Repository

class UpdateCatsUseCase(
    repository: Repository
): UpdateUseCase {
    private val _repository = repository
    override suspend operator fun invoke(): Result<Unit> {
        return _repository.updateItems()
    }

}