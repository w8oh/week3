package ru.sonya.week3.ui.viewModel

sealed interface MainUIEvent {
    object LoadEvent : MainUIEvent

    class OnItemClick(val item: FunCat): MainUIEvent
}

