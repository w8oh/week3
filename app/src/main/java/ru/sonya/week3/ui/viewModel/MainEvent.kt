package ru.sonya.week3.ui.viewModel

sealed interface MainEvent {
    data class OpenDetails(val cat: FunCat): MainEvent
}