package ru.sonya.week3.model.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [RoomCat::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDAO
}