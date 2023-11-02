package com.example.fizzbuzz.presentation.screens.end_game

sealed class EndGameScreenIntent {
    data class SetScore(val score: Int) : EndGameScreenIntent()
}