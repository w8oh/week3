package ru.sonya.week3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import ru.sonya.week3.view.LoadEvent
import ru.sonya.week3.view.MainEvent
import ru.sonya.week3.model.CatsRepository
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.view.MainState

class MainViewModel(
    private val repository: CatsRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _cats = MutableLiveData<MainState>()

    /*init {
        _cats.value =  MainState(cats = mutableListOf(FunCat("", "Cat", "it is fun")))
    }*/

    val cats: MutableLiveData<MainState>
        get() = _cats

    fun load(event: MainEvent) {
        when (event) {
            is LoadEvent -> {
                getCats()
            }
        }
    }

    fun getCats() {
        job = Coroutines.ioThenMain(
            { repository.getCats() },
            { _cats.value = it?.let { _it -> MainState(cats = _it) } }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}