package com.example.fizzbuzz.presentation.screens.sign_in

sealed class SignInScreenIntent {
    object SignIn : SignInScreenIntent()
    data class SetEmail(val email: String) : SignInScreenIntent()
    data class SetPassword(val password: String) : SignInScreenIntent()
    object SwitchPasswordVisibility : SignInScreenIntent()
}