package ru.sonya.week3.ui.viewModel

import ru.sonya.week3.domain.FunCat

sealed interface MainEvent {
    data class OpenDetails(val cat: DtoCat): MainEvent

}