package com.pollub.masterand.db.repositories.scores

import com.pollub.masterand.db.dao.ScoreDao
import com.pollub.masterand.db.entities.Score

class ScoresRepositoryImpl(private val scoreDao: ScoreDao) : ScoresRepository {
    override suspend fun insert(score: Score): Long = scoreDao.insert(score)
}