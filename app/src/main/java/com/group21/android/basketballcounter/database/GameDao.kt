package com.group21.android.basketballcounter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.group21.android.basketballcounter.Game
import java.util.*

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAll(): LiveData<List<Game>>

    @Query("SELECT * FROM game where id LIKE :id")
    fun findByID(id : UUID): LiveData<Game>

    @Insert
    fun insertAll(vararg games: Game)

    @Delete
    fun delete(game : Game)

    @Update
    fun updateGames(vararg games : Game)
}