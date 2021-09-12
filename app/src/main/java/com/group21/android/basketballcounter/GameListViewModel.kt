package com.group21.android.basketballcounter

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameListViewModel : ViewModel() {
    val games = mutableListOf<Game>()

    init {
        for (i in 0 until 100) {
            val game = Game()
            // will keep default names and scores at 0 for now.
            // if I want to be spicy, we can randomly generate some scores or names but eh
            game.index = i
            game.team1Score = Random.nextInt(0,100)
            game.team2Score = Random.nextInt(0,100)
            game.team1Name = getRandomString(8)
            game.team2Name = getRandomString(8)
            games += game
        }
    }


    private fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}