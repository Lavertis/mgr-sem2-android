package com.pollub.masterand.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var email: String,
)