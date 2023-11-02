package com.example.fizzbuzz.presentation.screens.end_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EndGameViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(EndGameScreenState())
    val state = _state.asStateFlow()

    fun processIntent(intent: EndGameScreenIntent) {
        when (intent) {
            is EndGameScreenIntent.SetScore -> {
                setState { copy(score = intent.score) }
            }
        }
    }

    private fun setState(stateReducer: EndGameScreenState.() -> EndGameScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}