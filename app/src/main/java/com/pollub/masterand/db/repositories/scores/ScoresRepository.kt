package com.pollub.masterand.db.repositories.scores

import com.pollub.masterand.db.entities.Score

interface ScoresRepository {
    suspend fun insert(score: Score): Long
}