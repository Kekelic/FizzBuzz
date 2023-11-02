package com.example.fizzbuzz.presentation.screens.sign_up

sealed class SignUpScreenIntent {
    object SignUp : SignUpScreenIntent()
    data class SetUsername(val username: String) : SignUpScreenIntent()
    data class SetEmail(val email: String) : SignUpScreenIntent()
    data class SetPassword(val password: String) : SignUpScreenIntent()
    object SwitchPasswordVisibility : SignUpScreenIntent()
}