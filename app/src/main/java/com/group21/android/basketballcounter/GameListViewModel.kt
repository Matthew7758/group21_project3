package com.group21.android.basketballcounter

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import kotlin.random.Random
import android.content.Context
import androidx.lifecycle.AndroidViewModel


class GameListViewModel(application: Application) : AndroidViewModel(application) {
    private val db = GameDatabase.getDatabase(application)
    private val gameDao = db.gameDao()
    val games : List<Game> = gameDao.getAll()

    private fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}