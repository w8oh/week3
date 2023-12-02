package ru.sonya.week3.ui.viewModel

import ru.sonya.week3.domain.FunCat

sealed interface MainUIEvent {
    object LoadEvent : MainUIEvent

    class OnItemClick(val item: DtoCat): MainUIEvent
}

