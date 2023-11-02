package com.example.fizzbuzz.presentation.screens.home

data class HomeScreenState(
    val username: String = "",
    val isSignOutLoading: Boolean = false,
    val isSignOutSuccess: Boolean = false,
    val signOutErrorMessage: String = ""
)
