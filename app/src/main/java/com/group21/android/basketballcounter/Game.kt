package com.group21.android.basketballcounter

import java.util.*

/**
 * data class for a basketball game
 */
data class Game(val id: UUID = UUID.randomUUID(),
                var team1Name: String = "",
                var team2Name: String = "",
                var team1Score: Int = 0,
                var team2Score: Int = 0,
                var index: Int = -1,
                var date: Date = Date(),) {


}