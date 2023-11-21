package ru.sonya.week3.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CatJson::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val catsDao: CatsDAO?
}