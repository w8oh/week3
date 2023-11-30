package ru.sonya.week3.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sonya.week3.model.roomDB.AppDatabase
import ru.sonya.week3.model.CatsRepository

class MainViewModelFactory(db: AppDatabase, _sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

    private val sharedPreferences = _sharedPreferences
    private val repository = CatsRepository(db, sharedPreferences)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }

}