package ru.sonya.week3.domain

class UpdateCatsUseCase(
    private val repository: Repository
): UpdateUseCase {
    override suspend operator fun invoke(): Result<Unit> {
        return repository.updateItems()
    }

}