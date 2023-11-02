package com.example.fizzbuzz.presentation.screens.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.R
import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.example.fizzbuzz.exceptions.AuthErrorException
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInScreenState())
    val state = _state.asStateFlow()

    fun processIntent(intent: SignInScreenIntent) {
        when (intent) {
            is SignInScreenIntent.SignIn -> {
                if (state.value.email.isEmpty()) {
                    setState {
                        copy(emailErrorMessage = R.string.please_enter_email)
                    }
                } else if (state.value.password.isEmpty()) {
                    setState {
                        copy(passwordErrorMessage = R.string.please_enter_password)
                    }
                } else {
                    viewModelScope.launch {
                        authRepository.signIn(
                            email = state.value.email,
                            password = state.value.password
                        ).collect {
                            processSignInResult(it)
                        }
                    }
                }
            }

            is SignInScreenIntent.SetEmail -> {
                setState {
                    copy(
                        email = intent.email,
                        emailErrorMessage = null,
                        signInErrorMessage = null
                    )
                }
            }

            is SignInScreenIntent.SetPassword -> {
                setState {
                    copy(
                        password = intent.password,
                        passwordErrorMessage = null,
                        signInErrorMessage = null
                    )
                }
            }

            is SignInScreenIntent.SwitchPasswordVisibility -> {
                setState { copy(isPasswordVisible = !state.value.isPasswordVisible) }
            }
        }
    }

    private fun processSignInResult(result: Result<AuthResult>) {
        when (result) {
            is Result.Success -> {
                setState {
                    copy(
                        isSignInLoading = false,
                        signInErrorMessage = null,
                        isSignInSuccess = true
                    )
                }
            }

            is Result.Loading -> {
                setState {
                    copy(
                        isSignInLoading = true,
                        signInErrorMessage = null,
                        isSignInSuccess = false
                    )
                }
            }

            is Result.Error -> {
                val authErrorMessage = AuthErrorException(result.errorCode ?: "")
                setState {
                    copy(
                        isSignInLoading = false,
                        isSignInSuccess = false,
                        signInErrorMessage = authErrorMessage.specificErrorMessage,
                        passwordErrorMessage = authErrorMessage.passwordErrorMessage,
                        emailErrorMessage = authErrorMessage.emailErrorMessage
                    )
                }
            }
        }
    }

    private fun setState(stateReducer: SignInScreenState.() -> SignInScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }

}