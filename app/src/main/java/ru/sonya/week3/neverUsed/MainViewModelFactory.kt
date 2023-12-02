package ru.sonya.week3.neverUsed

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sonya.week3.data.roomDB.AppDatabase
import ru.sonya.week3.data.CatsRepository
import ru.sonya.week3.domain.GetCatsUseCase
import ru.sonya.week3.domain.UpdateCatsUseCase

class MainViewModelFactory(db: AppDatabase, _sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

/*
    private val sharedPreferences = _sharedPreferences
    private val repository = CatsRepository(db, sharedPreferences)

    private val getCatsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetCatsUseCase(repository = repository)
    }

    private val updateCatsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        UpdateCatsUseCase(repository = repository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository = repository, getCatsUseCase = getCatsUseCase, updateCatsUseCase = updateCatsUseCase) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }
*/


}