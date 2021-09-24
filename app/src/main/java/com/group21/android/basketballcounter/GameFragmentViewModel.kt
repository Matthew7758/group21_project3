package com.group21.android.basketballcounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import android.util.Log
import java.io.File
import java.util.*

private const val TAG = "FRAGMENT_MAIN"

class GameFragmentViewModel : ViewModel() {
    private val gameRepository = GameRepository.get()
    private val gameIdLiveData = MutableLiveData<UUID>()
    var gameLiveData: LiveData<Game> =
        Transformations.switchMap(gameIdLiveData) { gameId ->
            gameRepository.getGame(gameId)
        }
    fun loadGame(gameId: UUID) {
        Log.d("FRAGMENT_MAIN", gameId.toString())
        gameIdLiveData.value = gameId
    }
    fun saveGame(game : Game) {
        Log.d("FRAGMENT_MAIN", "Saving Game")
        Log.d(TAG, "Game = $game")
        gameRepository.updateGame(game)
    }
    fun insertGame(game: Game) {
        Log.d("FRAGMENT_MAIN", "Inserting new game")
        Log.d(TAG, "Game = $game")
        gameRepository.addGame(game)
    }
    fun getPhoto1File(game: Game): File {
        return gameRepository.getPhoto1File(game)
    }
    fun getPhoto2File(game: Game): File {
        return gameRepository.getPhoto2File(game)
    }
    var score1 = 0
    var score2 = 0
    var team1 = "Team 1"
    var team2 = "Team 2"

    /*init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }*/

    fun threeTeam1() {
        score1 += 3
    }

    fun twoTeam1() {
        score1 += 2
    }

    fun oneTeam1() {
        score1++
    }

    fun threeTeam2() {
        score2 += 3
    }

    fun twoTeam2() {
        score2 += 2
    }

    fun oneTeam2() {
        score2++
    }

    fun resetScore() {
        score1 = 0
        score2 = 0
    }
}