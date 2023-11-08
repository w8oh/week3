package ru.sonya.week3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import ru.sonya.week3.Coroutines
import ru.sonya.week3.model.CatsRepository
import ru.sonya.week3.model.FunCat

class MainViewModel(
    private val repository: CatsRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _cats = MutableLiveData<List<FunCat>>()
    val cats: LiveData<List<FunCat>>
        get() = _cats

    fun getCats() {
        job = Coroutines.ioThenMain(
            { repository.getCats() },
            { _cats.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}