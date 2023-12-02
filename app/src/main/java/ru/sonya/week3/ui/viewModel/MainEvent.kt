package ru.sonya.week3.ui.viewModel

import ru.sonya.week3.domain.FunCat

sealed interface MainEvent {
    data class OpenDetails(val cat: DtoCat) : MainEvent
    data class FailureLoading(val exception: String) : MainEvent
    data class DoneLoading(val isProgressBarVisible: Boolean = false) : MainEvent
    data class StartLoading(val isProgressBarVisible: Boolean = true) : MainEvent

}