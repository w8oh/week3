package ru.sonya.week3.view

import ru.sonya.week3.model.FunCat

sealed interface MainUIEvent {
    object LoadEvent : MainUIEvent

    class OnItemClick(val item: FunCat): MainUIEvent
}

