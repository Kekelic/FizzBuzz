package com.example.fizzbuzz.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.example.fizzbuzz.domain.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        processIntent(HomeScreenIntent.GetUsername(authRepository.currentUser?.uid ?: ""))
    }

    fun processIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.SignOut -> {
                viewModelScope.launch {
                    authRepository.signOut().collect {
                        processSignOutResult(it)
                    }
                }
            }

            is HomeScreenIntent.GetUsername -> {
                viewModelScope.launch {
                    firestoreRepository.getUsername(intent.userID).collect {
                        processUsernameResult(it)
                    }
                }
            }
        }
    }

    private fun processSignOutResult(result: Result<Unit>) {
        when (result) {
            is Result.Success -> {
                setState {
                    copy(
                        isSignOutLoading = false,
                        signOutErrorMessage = "",
                        isSignOutSuccess = true
                    )
                }
            }

            is Result.Loading -> {
                setState {
                    copy(
                        isSignOutLoading = true,
                        signOutErrorMessage = "",
                        isSignOutSuccess = false
                    )
                }
            }

            is Result.Error -> {
                setState {
                    copy(
                        isSignOutLoading = false,
                        signOutErrorMessage = result.message!!,
                        isSignOutSuccess = false
                    )
                }
            }
        }
    }

    private fun processUsernameResult(result: Result<String>) {
        when (result) {
            is Result.Success -> {
                setState { copy(username = result.data!!) }
            }

            else -> {}
        }
    }

    private fun setState(stateReducer: HomeScreenState.() -> HomeScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}