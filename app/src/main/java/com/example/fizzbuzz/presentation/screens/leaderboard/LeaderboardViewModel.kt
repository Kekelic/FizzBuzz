package com.example.fizzbuzz.presentation.screens.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.model.ScoreResponse
import com.example.fizzbuzz.domain.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LeaderboardScreenState())
    val state = _state.asStateFlow()

    init {
        processIntent(LeaderboardScreenIntent.LoadLeaderboard)
    }

    fun processIntent(intent: LeaderboardScreenIntent) {
        when (intent) {
            is LeaderboardScreenIntent -> {
                viewModelScope.launch {
                    firestoreRepository.getLeaderboard().collect {
                        processResult(it)
                    }
                }
            }
        }
    }

    private fun processResult(result: Result<List<ScoreResponse>>) {
        when (result) {
            is Result.Success -> {
                setState { copy(scores = result.data!!, isLoading = false) }
            }

            is Result.Loading -> {
                setState { copy(isLoading = true) }
            }

            is Result.Error -> {
                setState { copy(errorMessage = result.message!!, isLoading = false) }
            }
        }
    }

    private fun setState(stateReducer: LeaderboardScreenState.() -> LeaderboardScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }

}