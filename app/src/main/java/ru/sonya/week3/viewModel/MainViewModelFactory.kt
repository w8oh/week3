package ru.sonya.week3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sonya.week3.model.CatsRepository

class MainViewModelFactory() : ViewModelProvider.Factory {

    private val repository = CatsRepository()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }

}