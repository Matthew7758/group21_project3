package com.group21.android.basketballcounter

import androidx.lifecycle.ViewModel

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {
    var score1 = 0
    var score2 = 0

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