package com.group21.android.basketballcounter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val threeTeam1 = findViewById<Button>(R.id.threeTeam1)
        threeTeam1.setOnClickListener {
            mainViewModel.threeTeam1()
            displayScore1(mainViewModel.score1)
        }
        val twoTeam1 = findViewById<Button>(R.id.twoTeam1)
        twoTeam1.setOnClickListener {
            mainViewModel.twoTeam1()
            displayScore1(mainViewModel.score1)

        }
        val oneTeam1 = findViewById<Button>(R.id.oneTeam1)
        oneTeam1.setOnClickListener {
            mainViewModel.oneTeam1()
            displayScore1(mainViewModel.score1)
        }
        val threeTeam2 = findViewById<Button>(R.id.threeTeam2)
        threeTeam2.setOnClickListener {
            mainViewModel.threeTeam2()
            displayScore2(mainViewModel.score2)

        }
        val twoTeam2 = findViewById<Button>(R.id.twoTeam2)
        twoTeam2.setOnClickListener {
            mainViewModel.twoTeam2()
            displayScore2(mainViewModel.score2)
        }
        val oneTeam2 = findViewById<Button>(R.id.oneTeam2)
        oneTeam2.setOnClickListener {
            mainViewModel.oneTeam2()
            displayScore2(mainViewModel.score2)
        }

        val resetBtn = findViewById<Button>(R.id.resetButton)
        resetBtn.setOnClickListener {
            mainViewModel.resetScore()
            displayScore1(mainViewModel.score1)
            displayScore2(mainViewModel.score2)
        }
        displayScore1(mainViewModel.score1)
        displayScore2(mainViewModel.score2)
        Log.d("MainActivity", "onCreate Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy Called")
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
