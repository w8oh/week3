package ru.sonya.week3.viewModel

import android.content.Context
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import ru.sonya.week3.AboutOneCat
import ru.sonya.week3.view.LoadEvent
import ru.sonya.week3.view.MainUIEvent
import ru.sonya.week3.model.CatsRepository
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.view.MainEvent
import ru.sonya.week3.view.MainFlow
import ru.sonya.week3.view.MainState
import kotlin.coroutines.coroutineContext

class MainViewModel(
    private val repository: CatsRepository
) : ViewModel() {

    private val screenState = MutableLiveData<MainState>()
    private var sharedFlow = MutableLiveData<MainFlow>()

    val cats: LiveData<MainState> get() = screenState

    fun onEvent(event: MainUIEvent) {
        when (event) {
            is LoadEvent -> {
                getCats()
            }
            is MainEvent -> {

            }
        }
    }

    private fun getCats() {
        screenState.value = MainState(repository.getCats())
    }

    private fun openOneCat(c: Context, cat: FunCat ) {

    }
}