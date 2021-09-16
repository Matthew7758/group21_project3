package com.group21.android.basketballcounter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * data class for a basketball game
 */
@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: UUID,
    @ColumnInfo(name = "teamAName") var teamAName: String,
    @ColumnInfo(name = "teamBName") var teamBName: String,
    @ColumnInfo(name = "teamAScore") var teamAScore: Int,
    @ColumnInfo(name = "teamBScore") var teamBScore: Int,
    @ColumnInfo(name = "date") var date: Int
)