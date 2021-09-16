package com.group21.android.basketballcounter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.group21.android.basketballcounter.database.GameDatabase


class GameListViewModel(application: Application) : AndroidViewModel(application) {
    /*private val db = GameDatabase.getDatabase(application)
    private val gameDao = db.gameDao()
    val games: List<Game> = gameDao.getAll()*/
    private val gameRepository = GameRepository.get()
    val gameListLiveData = gameRepository.getAll()
}