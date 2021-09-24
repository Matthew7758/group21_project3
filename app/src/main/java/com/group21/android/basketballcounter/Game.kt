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
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "teamAName") val teamAName: String,
    @ColumnInfo(name = "teamBName") val teamBName: String,
    @ColumnInfo(name = "teamAScore") val teamAScore: Int,
    @ColumnInfo(name = "teamBScore") val teamBScore: Int,
    @ColumnInfo(name = "date") val date: Date = Date()
) {
    val team1PhotoFileName get() = "IMG1_$id.jpg"
    val team2PhotoFileName get() = "IMG2_$id.jpg"
}