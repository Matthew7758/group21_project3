package com.group21.android.basketballcounter

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main.team1Name
import kotlinx.android.synthetic.main.fragment_main.team2Name
import kotlinx.android.synthetic.main.fragment_main.threeTeam1
import kotlinx.android.synthetic.main.fragment_main.threeTeam2
import kotlinx.android.synthetic.main.fragment_main.twoTeam1
import kotlinx.android.synthetic.main.fragment_main.twoTeam2
import kotlinx.android.synthetic.main.fragment_main.oneTeam1
import kotlinx.android.synthetic.main.fragment_main.oneTeam2
import kotlinx.android.synthetic.main.fragment_main.team1Score
import kotlinx.android.synthetic.main.fragment_main.team2Score
import kotlinx.android.synthetic.main.fragment_main.resetButton
import kotlinx.android.synthetic.main.fragment_main.saveButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "FRAGMENT_MAIN"
private const val SCORE1 = "score1"
private const val SCORE2 = "score2"
private const val TEAM1 = "team1"
private const val TEAM2 = "team2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_main.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_main : Fragment() {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var mp: MediaPlayer? = null

    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: Int? = null
    private var param3: String? = null
    private var param4: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(SCORE1)
            param2 = it.getInt(SCORE2)
            param3 = it.getString(TEAM1)
            param4 = it.getString(TEAM2)
        }
        mainViewModel.score1 = savedInstanceState?.getInt(SCORE1, 0) ?: 0
        mainViewModel.score2 = savedInstanceState?.getInt(SCORE2, 0) ?: 0
        mainViewModel.team1 = savedInstanceState?.getString(TEAM1, "Team 1") ?: "Team 1"
        mainViewModel.team2 = savedInstanceState?.getString(TEAM2, "Team 2") ?: "Team 2"
        Log.d(TAG, "onCreate Called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_main.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_main().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team1Name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                mainViewModel.team1 = team1Name.text.toString()
            }
        })
        team2Name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                mainViewModel.team2 = team2Name.text.toString()
            }

        })
        threeTeam1.setOnClickListener {
            mainViewModel.threeTeam1()
            playTriple()
            displayScore1(mainViewModel.score1)
        }
        twoTeam1.setOnClickListener {
            mainViewModel.twoTeam1()
            playDouble()
            displayScore1(mainViewModel.score1)

        }
        oneTeam1.setOnClickListener {
            mainViewModel.oneTeam1()
            playSingle()
            displayScore1(mainViewModel.score1)
        }
        threeTeam2.setOnClickListener {
            mainViewModel.threeTeam2()
            playTriple()
            displayScore2(mainViewModel.score2)

        }
        twoTeam2.setOnClickListener {
            mainViewModel.twoTeam2()
            playDouble()
            displayScore2(mainViewModel.score2)
        }
        oneTeam2.setOnClickListener {
            mainViewModel.oneTeam2()
            playSingle()
            displayScore2(mainViewModel.score2)
        }

        resetButton.setOnClickListener {
            mainViewModel.resetScore()
            displayScore1(mainViewModel.score1)
            displayScore2(mainViewModel.score2)
            displayTeamNames("Team 1", "Team 2")
        }
        saveButton.setOnClickListener {
            //TODO: Write code to inflate second view with intents and such.
            val intent = Intent(activity, Main2Activity::class.java)
            startActivityForResult(intent, 1)
        }
        displayScore1(mainViewModel.score1)
        displayScore2(mainViewModel.score2)
        displayTeamNames(mainViewModel.team1, mainViewModel.team2)
    }

    private fun displayScore1(score: Int) {
        team1Score.text = score.toString()
    }

    private fun displayScore2(score: Int) {
        team2Score.text = score.toString()
    }

    private fun displayTeamNames(t1: String, t2: String) {
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
            mp = MediaPlayer.create(activity, R.raw.mlgtriple)
            mp!!.isLooping = false
            mp!!.start()
        } else {
            stopSound()
            mp = MediaPlayer.create(activity, R.raw.mlgtriple)
            mp!!.isLooping = false
            mp!!.start()
        }
    }

    private fun playDouble() {
        if (mp == null) {
            mp = MediaPlayer.create(activity, R.raw.mlgairhorn)
            mp!!.isLooping = false
            mp!!.start()
        } else {
            stopSound()
            mp = MediaPlayer.create(activity, R.raw.mlgairhorn)
            mp!!.isLooping = false
            mp!!.start()
        }
    }

    private fun playSingle() {
        if (mp == null) {
            mp = MediaPlayer.create(activity, R.raw.hitmarker)
            mp!!.isLooping = false
            mp!!.start()
        } else {
            stopSound()
            mp = MediaPlayer.create(activity, R.raw.hitmarker)
            mp!!.isLooping = false
            mp!!.start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(SCORE1, mainViewModel.score1)
        outState.putInt(SCORE2, mainViewModel.score2)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TEST", "ONACTIVITYRESULT CALLED")
        Log.d("TEST", "requestCode = $requestCode")
        if (requestCode == 1) {
            Log.d("TEST", "resultCode = $resultCode")
            if (resultCode == AppCompatActivity.RESULT_OK) {
                Log.d("TEST", "RESULT_OK")
                val result = data?.getStringExtra("result")
                Log.d("TEST", result.toString())
                Toast.makeText(activity, result.toString(), Toast.LENGTH_SHORT).show()
            }
            if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                // Write your code if there's no result
                Toast.makeText(activity, "Unpog.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}