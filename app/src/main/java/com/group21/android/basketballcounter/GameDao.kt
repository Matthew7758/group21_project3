package com.group21.android.basketballcounter

import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Query("SELECT * FROM game where id LIKE :gid")
    fun findByID(gid : String): Game

    @Insert
    fun insertAll(vararg games: Game)

    @Delete
    fun delete(game : Game)

    @Update
    fun updateGames(vararg games : Game)
}