package ru.sonya.week3.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sonya.week3.domain.FunCat
import ru.sonya.week3.domain.GetUseCase
import ru.sonya.week3.domain.UpdateUseCase
import ru.sonya.week3.domain.mapToDomain
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateUseCase: UpdateUseCase,
    getUseCase: GetUseCase
) : ViewModel() {

    private val screenState = MutableLiveData<MainState>()
    private val _screenEvent = SingleLiveEvent<MainEvent>()

    val screenEvent: LiveData<MainEvent> get() = _screenEvent
    val cats: LiveData<MainState> get() = screenState

    init {
        getUseCase().onEach { cats ->
            val dtoCats: List<DtoCat> = cats.map { it.mapToDomain() }
            screenState.postValue(MainState(dtoCats))
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.LoadEvent -> getCats()
            is MainUIEvent.OnItemClick -> openOneCat(event.item)
        }
    }

    private fun getCats() {
        viewModelScope.launch(Dispatchers.IO) {

            _screenEvent.postValue(MainEvent.StartLoading())

            updateUseCase.invoke().onFailure {
                _screenEvent.postValue(MainEvent.FailureLoading(it.message.toString()))
            }

            _screenEvent.postValue(MainEvent.DoneLoading())
        }
    }


    private fun openOneCat(cat: DtoCat) {
        _screenEvent.value = MainEvent.OpenDetails(cat)
    }
}