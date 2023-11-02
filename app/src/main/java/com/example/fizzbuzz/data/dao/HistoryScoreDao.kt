package com.example.fizzbuzz.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.fizzbuzz.domain.model.HistoryScore
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryScoreDao {
    @Insert
    suspend fun insertScore(historyScore: HistoryScore)

    @Query("SELECT * FROM history_score WHERE userID = :userID ORDER BY value DESC")
    fun getScoreOrderedByScore(userID: String): Flow<List<HistoryScore>>

    @Delete
    suspend fun deleteScore(historyScore: HistoryScore)
}