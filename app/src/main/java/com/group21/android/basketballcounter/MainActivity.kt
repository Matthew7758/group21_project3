package com.group21.android.basketballcounter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var score1 = 0
    var score2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "onCreate Called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy Called")
    }

    fun threeTeam1(view: View) {
        score1 += 3
        displayScore1(score1)
    }

    fun twoTeam1(view: View) {
        score1 += 2
        displayScore1(score1)
    }

    fun oneTeam1(view: View) {
        score1++
        displayScore1(score1)
    }

    fun threeTeam2(view: View) {
        score2 += 3
        displayScore2(score2)
    }

    fun twoTeam2(view: View) {
        score2 += 2
        displayScore2(score2)
    }

    fun oneTeam2(view: View) {
        score2++
        displayScore2(score2)
    }

    fun resetScore(view: View) {
        score1 = 0
        score2 = 0
        displayScore1(score1)
        displayScore2(score2)
    }

    private fun displayScore1(score: Int) {
        val scoreView1: TextView = findViewById(R.id.team1Score)
        scoreView1.text = score.toString()
    }

    private fun displayScore2(score: Int) {
        val scoreView2: TextView = findViewById(R.id.team2Score)
        scoreView2.text = score.toString()
    }

}
