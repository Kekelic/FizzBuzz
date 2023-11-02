package com.example.fizzbuzz.presentation.screens.sign_up

import androidx.annotation.StringRes

data class SignUpScreenState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSignUpLoading: Boolean = false,
    val isSignUpSuccess: Boolean = false,
    @StringRes val usernameErrorMessage: Int? = null,
    @StringRes val emailErrorMessage: Int? = null,
    @StringRes val passwordErrorMessage: Int? = null,
    @StringRes val signUpErrorMessage: Int? = null,
)