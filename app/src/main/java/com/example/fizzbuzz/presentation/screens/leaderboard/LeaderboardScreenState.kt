package com.example.fizzbuzz.presentation.screens.leaderboard

import com.example.fizzbuzz.domain.model.ScoreResponse

data class LeaderboardScreenState(
    val scores: List<ScoreResponse> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)