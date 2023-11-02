package com.example.fizzbuzz.presentation.screens.sign_in

import androidx.annotation.StringRes

data class SignInScreenState(
    val email: String = "",
    val password: String = "",
    val isSignInLoading: Boolean = false,
    val isSignInSuccess: Boolean = false,
    val isPasswordVisible: Boolean = false,
    @StringRes val emailErrorMessage: Int? = null,
    @StringRes val passwordErrorMessage: Int? = null,
    @StringRes val signInErrorMessage: Int? = null,
)
