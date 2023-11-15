package ru.sonya.week3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sonya.week3.view.MainUIEvent
import ru.sonya.week3.model.CatsRepository
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.view.CatItem
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
        var cats = repository.getCats()
        var catsList: List<FunCat> = cats!!.map { FunCat(it.url, it.breeds[0].name, it.breeds[0].description)}
        screenState.value = MainState(catsList)
    }

    private fun openOneCat(cat: FunCat ) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}