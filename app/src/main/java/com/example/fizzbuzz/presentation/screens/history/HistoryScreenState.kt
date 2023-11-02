package com.example.fizzbuzz.presentation.screens.history

import com.example.fizzbuzz.domain.model.HistoryScore

data class HistoryScreenState(
    val scores: List<HistoryScore> = emptyList(),
)