package com.example.fizzbuzz.domain.repository

import com.example.fizzbuzz.domain.model.HistoryScore
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getScoresFromRoom(userID: String): Flow<List<HistoryScore>>

    suspend fun addScoreToRoom(historyScore: HistoryScore)

    suspend fun deleteScoreFromRoom(historyScore: HistoryScore)
}