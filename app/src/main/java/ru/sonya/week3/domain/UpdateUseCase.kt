package ru.sonya.week3.domain

interface UpdateUseCase {
    suspend operator fun invoke(): Result<Unit>
}