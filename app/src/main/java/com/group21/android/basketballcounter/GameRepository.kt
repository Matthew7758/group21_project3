package com.group21.android.basketballcounter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.group21.android.basketballcounter.database.GameDatabase
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "game-database"
class GameRepository private constructor(context: Context){
    private val database : GameDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val gameDao = database.gameDao()

    fun getAll(): LiveData<List<Game>> = gameDao.getAll()
    fun getGame(id: UUID): LiveData<Game> = gameDao.findByID(id)
    //TODO: Insert game and delete game

    companion object {
        private var INSTANCE: GameRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GameRepository(context)
            }
        }
        fun get(): GameRepository {
            return INSTANCE ?:
            throw IllegalStateException("GameRepository must be initialized.")
        }
    }
}