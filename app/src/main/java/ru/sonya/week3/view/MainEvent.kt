package ru.sonya.week3.view

import ru.sonya.week3.model.FunCat

sealed interface MainEvent {
    data class OpenDetails(val cat: FunCat): MainEvent
}