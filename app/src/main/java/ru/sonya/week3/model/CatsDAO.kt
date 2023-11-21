package ru.sonya.week3.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CatsDAO {

    @Insert
    fun insert(vararg cats: RoomCat)

    @Delete
    fun delete(cat: RoomCat)

    @Query("SELECT * FROM DBCats")
    fun getAllCats(): List<RoomCat>?

    @Query("DELETE FROM DBCats")
    fun deleteAllCats(): Void

}