package ru.sonya.week3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sonya.week3.model.CatsRepository

class MainViewModel(
    private val repository: CatsRepository
) : ViewModel() {

    private val screenState = MutableLiveData<MainState>()
    private val _screenEvent = SingleLiveEvent<MainEvent>()

    init {
        viewModelScope.launch {
             repository.getItemsFlow().collect { cats ->
                 val funCats: List<FunCat> = cats.map {
                     FunCat( image = it.url, title = it.name, subtitle = it.description )
                 }.orEmpty()

                 screenState.postValue(MainState(funCats))
             }
        }
    }

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
            repository.updateItems()
            //надо вернуть резалт и в случае ошибки вывести тоаст месседж в МэинАктивити? или че или как
        }
    }


    private fun openOneCat(cat: FunCat) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}