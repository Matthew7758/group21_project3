package com.group21.android.basketballcounter

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


private const val TAG = "MainActivity"
private const val SCORE1 = "score1"
private const val SCORE2 = "score2"
private const val TEAM1 = "team1"
private const val TEAM2 = "team2"

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
        mainViewModel.team1 = savedInstanceState?.getString(TEAM1, "Team 1") ?: "Team 1"
        mainViewModel.team2 = savedInstanceState?.getString(TEAM2, "Team 2") ?: "Team 2"
        val editableT1 = findViewById<EditText>(R.id.team1Name)
        val editableT2 = findViewById<EditText>(R.id.team2Name)
        editableT1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                mainViewModel.team1 = editableT1.text.toString()
            }
        })
        editableT2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                mainViewModel.team2 = editableT2.text.toString()
            }

        })
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
            displayTeamNames("Team 1", "Team 2")
        }
        val saveBtn = findViewById<Button>(R.id.saveButton)
        saveBtn.setOnClickListener {
            //TODO: Write code to inflate second view with intents and such.
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivityForResult(intent, 1)
        }
        displayScore1(mainViewModel.score1)
        displayScore2(mainViewModel.score2)
        displayTeamNames(mainViewModel.team1, mainViewModel.team2)
        Log.d(TAG, "onCreate Called")
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TEST", "ONACTIVITYRESULT CALLED")
        Log.d("TEST", "requestCode = $requestCode")
        if (requestCode === 1) {
            Log.d("TEST", "resultCode = $resultCode")
            if (resultCode === RESULT_OK) {
                Log.d("TEST", "RESULT_OK")
                val result = data?.getStringExtra("result")
                Log.d("TEST", result.toString())
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
            }
            if (resultCode === RESULT_CANCELED) {
                // Write your code if there's no result
                Toast.makeText(applicationContext, "Unpog.", Toast.LENGTH_SHORT).show()
            }
        }
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
    private fun displayTeamNames(t1: String, t2: String) {
        val team1Name: EditText = findViewById(R.id.team1Name)
        val team2Name: EditText = findViewById(R.id.team2Name)
        team1Name.text = Editable.Factory.getInstance().newEditable(t1)
        team2Name.text = Editable.Factory.getInstance().newEditable(t2)
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
