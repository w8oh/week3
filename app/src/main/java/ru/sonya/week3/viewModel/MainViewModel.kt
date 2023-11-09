package ru.sonya.week3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sonya.week3.view.MainUIEvent
import ru.sonya.week3.model.CatsRepository
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.view.MainEvent
import ru.sonya.week3.view.MainState

class MainViewModel(
    private val repository: CatsRepository
) : ViewModel() {

    private val screenState = MutableLiveData<MainState>()
    private val _screenEvent = MutableLiveData<MainEvent>()

    val screenEvent: LiveData<MainEvent> get() = _screenEvent
    val cats: LiveData<MainState> get() = screenState

    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.LoadEvent -> getCats()
            is MainUIEvent.OnItemClick -> openOneCat(event.item)
        }
    }

    private fun getCats() {
        screenState.value = MainState(repository.getCats())
    }

    private fun openOneCat(cat: FunCat ) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}