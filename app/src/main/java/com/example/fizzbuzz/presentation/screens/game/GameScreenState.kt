package com.example.fizzbuzz.presentation.screens.game

import androidx.annotation.StringRes

data class GameScreenState(
    val number: Int = 1,
    @StringRes val scoreResultMessage: Int? = null,
    val isSaveScoreLoading: Boolean = false,
    val saveScoreErrorMessage: String = ""
)
