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
import kotlinx.android.synthetic.main.game_fragment.*
import java.util.*


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
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_GAME_ID = "game_id"

class GameFragment : Fragment() {
    private lateinit var game: Game
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
        val gameId : UUID
        if(arguments?.getSerializable(ARG_GAME_ID) != null)
            gameId = arguments?.getSerializable(ARG_GAME_ID) as UUID
        else
            gameId = UUID.randomUUID()
        Log.d(TAG, "Args bundle game ID = $gameId")
        //TODO: Load crime from database.

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
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    companion object {
        fun newInstance(gameId: UUID): GameFragment {
            val args = Bundle().apply {
                putSerializable(ARG_GAME_ID, gameId)
            }
            return GameFragment().apply {
                arguments = args
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
        displayButton.setOnClickListener {
            val nextFrag = GameListFragment.newInstance()
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(((view as ViewGroup).parent as View).id, nextFrag)
            ft.addToBackStack(null)
            ft.commit()
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
        outState.putString(TEAM1, mainViewModel.team1)
        outState.putString(TEAM2, mainViewModel.team2)
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