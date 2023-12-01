package ru.sonya.week3.data.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CatsDAO {

    @Insert
    fun insertAll(cats:List<RoomCat>)

    @Delete
    fun delete(cat: RoomCat)

    @Query("SELECT * FROM DBCats")
    fun getAllCats(): Flow<List<RoomCat>>

    @Query("DELETE FROM DBCats")
    fun deleteAllCats()

}