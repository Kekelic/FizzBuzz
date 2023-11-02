package com.example.fizzbuzz.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen("Sign in")
    object SignUp : Screen("Sign up")
    object Home : Screen("Home")
    object Game : Screen("Game")
    object EndGame : Screen("EndGame")
    object Leaderboard : Screen("Leaderboard")
    object History : Screen("History")
}