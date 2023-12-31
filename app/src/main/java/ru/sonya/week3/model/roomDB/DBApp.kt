package ru.sonya.week3.model.roomDB

import android.app.Application
import androidx.room.Room.databaseBuilder

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