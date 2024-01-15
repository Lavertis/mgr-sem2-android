package com.pollub.masterand.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val score: Long = 0,
    val playerId: Long
)