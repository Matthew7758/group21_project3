package com.group21.android.basketballcounter

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.group21.android.basketballcounter.database.GameDatabase
import java.io.File
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "game-database"
class GameRepository private constructor(context: Context){
    private val database : GameDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val gameDao = database.gameDao()
    private val executor = Executors.newSingleThreadExecutor()
    private val filesDir = context.applicationContext.filesDir
    fun getAll(): LiveData<List<Game>> = gameDao.getAll()
    fun getGame(id: UUID): LiveData<Game> = gameDao.findByID(id)
    //TODO: Insert game and delete game
    fun updateGame(game: Game) {
        Log.d("FRAGMENT_MAIN", "GameRepository game = $game")
        executor.execute{gameDao.update(game)}}
    fun deleteGame(game: Game) {executor.execute{gameDao.delete(game)}}
    fun addGame(game: Game) {executor.execute{gameDao.insert(game)}}
    fun getPhoto1File(game: Game): File = File(filesDir, game.team1PhotoFileName)
    fun getPhoto2File(game: Game): File = File(filesDir, game.team2PhotoFileName)
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