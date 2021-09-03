package com.group21.android.basketballcounter

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val SCORE1 = "score1"
private const val SCORE2 = "score2"

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    var mp: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.score1 = savedInstanceState?.getInt(SCORE1, 0) ?: 0
        mainViewModel.score2 = savedInstanceState?.getInt(SCORE2, 0) ?: 0

        val threeTeam1 = findViewById<Button>(R.id.threeTeam1)
        threeTeam1.setOnClickListener {
            mainViewModel.threeTeam1()
            playTriple()
            displayScore1(mainViewModel.score1)
        }
        val twoTeam1 = findViewById<Button>(R.id.twoTeam1)
        twoTeam1.setOnClickListener {
            mainViewModel.twoTeam1()
            playDouble()
            displayScore1(mainViewModel.score1)

        }
        val oneTeam1 = findViewById<Button>(R.id.oneTeam1)
        oneTeam1.setOnClickListener {
            mainViewModel.oneTeam1()
            playSingle()
            displayScore1(mainViewModel.score1)
        }
        val threeTeam2 = findViewById<Button>(R.id.threeTeam2)
        threeTeam2.setOnClickListener {
            mainViewModel.threeTeam2()
            playTriple()
            displayScore2(mainViewModel.score2)

        }
        val twoTeam2 = findViewById<Button>(R.id.twoTeam2)
        twoTeam2.setOnClickListener {
            mainViewModel.twoTeam2()
            playDouble()
            displayScore2(mainViewModel.score2)
        }
        val oneTeam2 = findViewById<Button>(R.id.oneTeam2)
        oneTeam2.setOnClickListener {
            mainViewModel.oneTeam2()
            playSingle()
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
        Log.d(TAG, "onCreate Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(SCORE1, mainViewModel.score1)
        outState.putInt(SCORE2, mainViewModel.score2)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    private fun displayScore1(score: Int) {
        val scoreView1: TextView = findViewById(R.id.team1Score)
        scoreView1.text = score.toString()
    }

    private fun displayScore2(score: Int) {
        val scoreView2: TextView = findViewById(R.id.team2Score)
        scoreView2.text = score.toString()
    }

    fun stopSound() {
        if (mp != null) {
            mp!!.stop()
            mp!!.release()
            mp = null
        }
    }

    private fun playTriple() {
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.mlgtriple)
            mp!!.isLooping = false
            mp!!.start()
        } else {
            stopSound()
            mp = MediaPlayer.create(this, R.raw.mlgtriple)
            mp!!.isLooping = false
            mp!!.start()
        }

    }

    private fun playDouble() {
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.mlgairhorn)
            mp!!.isLooping = false
            mp!!.start()
        } else {
            stopSound()
            mp = MediaPlayer.create(this, R.raw.mlgairhorn)
            mp!!.isLooping = false
            mp!!.start()
        }
    }

    private fun playSingle() {
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.hitmarker)
            mp!!.isLooping = false
            mp!!.start()
        } else {
            stopSound()
            mp = MediaPlayer.create(this, R.raw.hitmarker)
            mp!!.isLooping = false
            mp!!.start()
        }
    }

}
