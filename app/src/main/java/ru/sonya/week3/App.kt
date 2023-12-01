package ru.sonya.week3

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

  /* lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = databaseBuilder(
            this,
            AppDatabase::class.java, "cats-database"
        ).build()

    }*/

}