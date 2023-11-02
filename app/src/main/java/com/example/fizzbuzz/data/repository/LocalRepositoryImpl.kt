package com.example.fizzbuzz.data.repository

import com.example.fizzbuzz.data.dao.HistoryScoreDao
import com.example.fizzbuzz.domain.model.HistoryScore
import com.example.fizzbuzz.domain.repository.LocalRepository

class LocalRepositoryImpl(
    private val historyScoreDao: HistoryScoreDao
) : LocalRepository {

    override fun getScoresFromRoom(userID: String) = historyScoreDao.getScoreOrderedByScore(userID)

    override suspend fun addScoreToRoom(historyScore: HistoryScore) =
        historyScoreDao.insertScore(historyScore)

    override suspend fun deleteScoreFromRoom(historyScore: HistoryScore) =
        historyScoreDao.deleteScore(historyScore)
}