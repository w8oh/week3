package ru.sonya.week3.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CatsDAO {

    @Insert
    fun insertAll(vararg cats: CatJson)

    @Delete
    fun delete(cat: CatJson)

    @Query("SELECT * FROM DBCats")
    fun getAllCats(): List<CatJson>?

}