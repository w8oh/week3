package ru.sonya.week3.viewModel

sealed interface MainUIEvent {
    object LoadEvent : MainUIEvent

    class OnItemClick(val item: FunCat): MainUIEvent
}

