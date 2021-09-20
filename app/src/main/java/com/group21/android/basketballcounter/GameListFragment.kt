package com.group21.android.basketballcounter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "GameListFragment"
private var GAME_STATUS = ""

class GameListFragment : Fragment() {

    //Required interface for hosting activities
    interface Callbacks {
        fun onGameSelected(gameId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = GameAdapter(emptyList())

    private val gameListViewModel: GameListViewModel by lazy {
        ViewModelProviders.of(this).get(GameListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("GameStatus")?.let {
            GAME_STATUS = it
        }
        Log.d(TAG, "GameStatus = $GAME_STATUS")
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView Entered")
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gameRecyclerView =
            view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)
        gameRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameListViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            { games ->
                games?.let {
                    var gamesList : MutableList<Game> = mutableListOf()
                    for (game : Game in games) {
                        if (GAME_STATUS == "T")
                            gamesList.add(game)
                        else if (GAME_STATUS == "A") {
                            if (game.teamAScore > game.teamBScore)
                                gamesList.add(game)
                        } else if (GAME_STATUS == "B") {
                            if (game.teamBScore > game.teamAScore)
                                gamesList.add(game)
                        }
                    }
                    Log.i(TAG, "Got games ${games.size}")
                    Log.i(TAG, "Loading games ${gamesList.size}")
                    updateUI(gamesList)
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(games: List<Game>) {
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }

    private inner class GameHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var game: Game
        val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        val teamsTextView: TextView = itemView.findViewById(R.id.team_titles)
        val teamScores: TextView = itemView.findViewById(R.id.team_scores)
        val teamImage: ImageView = itemView.findViewById(R.id.teamImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(game: Game) {
            this.game = game
            dateTextView.text = this.game.date.toString()
            teamsTextView.text =
                "Team ".plus(this.game.teamAName).plus(":Team ").plus(this.game.teamBName)
            teamScores.text = String.format("%d", game.teamAScore).plus(":")
                .plus(String.format("%d", game.teamBScore))
            if (this.game.teamAScore > this.game.teamBScore) {
                teamImage.setImageResource(R.drawable.vinnypog)
            } else {
                teamImage.setImageResource(R.drawable.vandarkholme)
            }

        }

        override fun onClick(v: View?) {
            callbacks?.onGameSelected(game.id)
        }

    }

    private inner class GameAdapter(var games: List<Game>) : RecyclerView.Adapter<GameHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size
        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            //Log.d(TAG, "gameID Upon Binding = ${game.id}")
            holder.bind(game)

        }
    }


    companion object { //Part 6
        fun newInstance(gameStatus: String): GameListFragment {
            val args = Bundle().apply {
                putString("GameStatus", gameStatus)
            }
            return GameListFragment().apply {
                arguments = args
            }
        }
    }
}