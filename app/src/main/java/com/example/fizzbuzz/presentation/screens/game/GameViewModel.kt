package com.example.fizzbuzz.presentation.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.model.HistoryScore
import com.example.fizzbuzz.domain.model.Score
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.example.fizzbuzz.domain.repository.FirestoreRepository
import com.example.fizzbuzz.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firebaseRepository: FirestoreRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GameScreenState())
    val state = _state.asStateFlow()

    fun processIntent(intent: GameScreenIntent) {
        val currentNumber = state.value.number
        when (intent) {
            is GameScreenIntent.Fizz -> {
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    continueGame(currentNumber)
                } else {
                    endGame(currentNumber)
                }

            }

            is GameScreenIntent.Buzz -> {
                if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                    continueGame(currentNumber)
                } else {
                    endGame(currentNumber)
                }
            }

            is GameScreenIntent.FizzBuzz -> {
                if (currentNumber % 15 == 0) {
                    continueGame(currentNumber)
                } else {
                    endGame(currentNumber)
                }
            }

            is GameScreenIntent.Next -> {
                if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                    continueGame(currentNumber)
                } else {
                    endGame(currentNumber)
                }
            }
        }
    }

    private fun continueGame(currentNumber: Int) {
        setState { copy(number = currentNumber.plus(1)) }
    }

    private fun endGame(currentNumber: Int) = viewModelScope.launch {
        val userID = authRepository.currentUser!!.uid
        localRepository.addScoreToRoom(
            HistoryScore(
                value = currentNumber,
                userID = userID,
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    ?: ""
            )
        )
        firebaseRepository.saveScore(Score(value = currentNumber, userID = userID))
            .collect { result ->
                processSaveScoreResult(result)
            }
    }

    private fun processSaveScoreResult(result: Result<Int>) {
        when (result) {
            is Result.Success -> {
                setState {
                    copy(
                        scoreResultMessage = result.data!!,
                        isSaveScoreLoading = false,
                        saveScoreErrorMessage = ""
                    )
                }
            }

            is Result.Loading -> {
                setState {
                    copy(
                        scoreResultMessage = null,
                        isSaveScoreLoading = true,
                        saveScoreErrorMessage = ""
                    )
                }
            }

            is Result.Error -> {
                setState {
                    copy(
                        scoreResultMessage = null,
                        isSaveScoreLoading = false,
                        saveScoreErrorMessage = result.message!!
                    )
                }
            }
        }
    }

    private fun setState(stateReducer: GameScreenState.() -> GameScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }

}