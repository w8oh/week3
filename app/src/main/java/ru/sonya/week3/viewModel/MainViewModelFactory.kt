package ru.sonya.week3.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sonya.week3.model.CatsRepository

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository = CatsRepository()
    private  val _context = context
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(_context,repository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }

}