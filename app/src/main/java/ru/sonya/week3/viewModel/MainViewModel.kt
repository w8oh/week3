package ru.sonya.week3.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.sonya.week3.model.CatJson
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
    private val _screenEvent = SingleLiveEvent<MainEvent>()

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
            val cats = repository.getCats()
            val funCats: Result<List<FunCat>?>
                    = cats.map { value ->  value?.map { it -> FunCat(image=it.url, title=it.name, subtitle=it.description) } }

            screenState.postValue(MainState(funCats))
        }
    }

    private fun openOneCat(cat: FunCat) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}