package ru.sonya.week3.data.roomDB

import android.app.Application
import androidx.room.Room.databaseBuilder
import dagger.hilt.android.HiltAndroidApp

/*@HiltAndroidApp*/
class DBApp : Application() {

   lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = databaseBuilder(
            this,
            AppDatabase::class.java, "cats-database"
        ).build()

    }

}