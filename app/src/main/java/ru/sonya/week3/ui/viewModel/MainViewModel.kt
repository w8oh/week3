package ru.sonya.week3.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sonya.week3.data.CatsRepository
import ru.sonya.week3.domain.GetCatsUseCase
import ru.sonya.week3.domain.UpdateCatsUseCase

class MainViewModel(
    private val repository: CatsRepository
) : ViewModel() {

    private val screenState = MutableLiveData<MainState>()
    private val _screenEvent = SingleLiveEvent<MainEvent>()

    init {
        val getCatsUseCase = GetCatsUseCase(repository)
        getCatsUseCase.invoke().onEach { cats ->
            val funCats: List<FunCat> = cats.map {
                FunCat(image = it.url, title = it.name, subtitle = it.description)
            }
            screenState.postValue(MainState(funCats))
        }.launchIn(viewModelScope)
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
            //TODO Показать загрузку
            val updateCatsUseCase = UpdateCatsUseCase(repository)
            updateCatsUseCase.invoke().onFailure  {
                    //TODO Ошибка загрузки
            }
            //TODO Скрыть загрузку
            //
            // У тебя есть MainEvent, в котором можно создать object\class ошибки и эмитить в Activity через _screenEvent
            // это оставлю на десерт, хочу пока с остальным разобраться

        }
    }


    private fun openOneCat(cat: FunCat) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}