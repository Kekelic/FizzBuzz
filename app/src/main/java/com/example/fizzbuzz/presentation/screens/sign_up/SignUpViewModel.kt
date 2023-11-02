package com.example.fizzbuzz.presentation.screens.sign_up

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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    fun processIntent(intent: SignUpScreenIntent) {
        when (intent) {
            is SignUpScreenIntent.SignUp -> {
                if (state.value.username.isEmpty()) {
                    setState {
                        copy(usernameErrorMessage = R.string.please_enter_username)
                    }
                } else if (state.value.email.isEmpty()) {
                    setState {
                        copy(emailErrorMessage = R.string.please_enter_email)
                    }
                } else if (state.value.password.isEmpty()) {
                    setState {
                        copy(passwordErrorMessage = R.string.please_enter_password)
                    }
                } else {
                    viewModelScope.launch {
                        authRepository.signUp(
                            username = state.value.username,
                            email = state.value.email,
                            password = state.value.password
                        ).collect {
                            processSignUpResult(it)
                        }
                    }
                }

            }

            is SignUpScreenIntent.SetUsername -> {
                setState {
                    copy(
                        username = intent.username,
                        usernameErrorMessage = null,
                        signUpErrorMessage = null
                    )
                }
            }

            is SignUpScreenIntent.SetEmail -> {
                setState {
                    copy(
                        email = intent.email, emailErrorMessage = null, signUpErrorMessage = null
                    )
                }
            }

            is SignUpScreenIntent.SetPassword -> {
                setState {
                    copy(
                        password = intent.password,
                        passwordErrorMessage = null,
                        signUpErrorMessage = null
                    )
                }
            }

            is SignUpScreenIntent.SwitchPasswordVisibility -> {
                setState { copy(isPasswordVisible = !state.value.isPasswordVisible) }
            }
        }
    }

    private fun processSignUpResult(result: Result<AuthResult>) {
        when (result) {
            is Result.Success -> {
                setState {
                    copy(
                        isSignUpLoading = false, signUpErrorMessage = null, isSignUpSuccess = true
                    )
                }
            }

            is Result.Loading -> {
                setState {
                    copy(
                        isSignUpLoading = true, signUpErrorMessage = null, isSignUpSuccess = false
                    )
                }
            }

            is Result.Error -> {
                val authErrorMessage = AuthErrorException(result.errorCode ?: "")
                setState {
                    copy(
                        isSignUpLoading = false,
                        isSignUpSuccess = false,
                        usernameErrorMessage = null,
                        signUpErrorMessage = authErrorMessage.specificErrorMessage,
                        passwordErrorMessage = authErrorMessage.passwordErrorMessage,
                        emailErrorMessage = authErrorMessage.emailErrorMessage
                    )
                }
            }
        }
    }

    private fun setState(stateReducer: SignUpScreenState.() -> SignUpScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}