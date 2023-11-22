package ru.sonya.week3.viewModel

sealed interface MainEvent {
    data class OpenDetails(val cat: FunCat): MainEvent
}