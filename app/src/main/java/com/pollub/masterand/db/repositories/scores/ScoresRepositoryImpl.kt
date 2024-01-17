package com.pollub.masterand.db.repositories.scores

import com.pollub.masterand.db.dao.ScoreDao
import com.pollub.masterand.db.entities.Score
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScoresRepositoryImpl @Inject constructor(private val scoreDao: ScoreDao) : ScoresRepository {
    override suspend fun insert(score: Score): Long = scoreDao.insert(score)
}