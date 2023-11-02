package com.example.fizzbuzz.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.example.fizzbuzz.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryScreenState())
    val state = _state.asStateFlow()

    init {
        processIntent(HistoryScreenIntent.LoadHistoryScores)
    }

    fun processIntent(intent: HistoryScreenIntent) {
        when (intent) {
            is HistoryScreenIntent -> {
                viewModelScope.launch {
                    localRepository.getScoresFromRoom(authRepository.currentUser!!.uid).collect {
                        setState { copy(scores = it) }
                    }
                }
            }
        }
    }

    private fun setState(stateReducer: HistoryScreenState.() -> HistoryScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }


}