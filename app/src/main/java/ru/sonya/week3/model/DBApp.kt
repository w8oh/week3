package ru.sonya.week3.model

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room.databaseBuilder
import java.util.Date

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