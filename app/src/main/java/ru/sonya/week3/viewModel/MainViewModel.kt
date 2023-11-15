package ru.sonya.week3.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.sonya.week3.view.MainUIEvent
import ru.sonya.week3.model.CatsRepository
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.view.CatItem
import ru.sonya.week3.view.MainEvent
import ru.sonya.week3.view.MainState

class MainViewModel(
    private val context: Context,
    private val repository: CatsRepository
) : ViewModel() {

    private val screenState = MutableLiveData<MainState>()
    private val _screenEvent = MutableLiveData<MainEvent>()

    val _context = context

    val screenEvent: LiveData<MainEvent> get() = _screenEvent
    val cats: LiveData<MainState> get() = screenState

    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.LoadEvent -> getCats()
            is MainUIEvent.OnItemClick -> openOneCat(event.item)
        }
    }

    private fun getCats() {
        viewModelScope.launch(Dispatchers.IO) {
            val cats = repository.getCats(_context)
            val catsList: List<FunCat> =
                cats!!.map { FunCat(it.url, it.breeds[0].name, it.breeds[0].description) }
            screenState.postValue(MainState(catsList))
        }
    }

    private fun openOneCat(cat: FunCat) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}