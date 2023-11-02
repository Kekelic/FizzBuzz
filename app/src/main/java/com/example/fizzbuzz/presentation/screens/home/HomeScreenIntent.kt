package com.example.fizzbuzz.presentation.screens.home

sealed class HomeScreenIntent {
    object SignOut : HomeScreenIntent()
    data class GetUsername(val userID: String) : HomeScreenIntent()
}