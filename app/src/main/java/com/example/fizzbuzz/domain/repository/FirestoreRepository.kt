package com.example.fizzbuzz.domain.repository

import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.model.Score
import com.example.fizzbuzz.domain.model.ScoreResponse
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun getUsername(Id: String): Flow<Result<String>>
    fun saveScore(score: Score): Flow<Result<Int>>
    fun getLeaderboard(): Flow<Result<List<ScoreResponse>>>
}