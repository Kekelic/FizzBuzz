package com.example.fizzbuzz.presentation.screens.game

sealed class GameScreenIntent {
    object Fizz : GameScreenIntent()
    object FizzBuzz : GameScreenIntent()
    object Buzz : GameScreenIntent()
    object Next : GameScreenIntent()
}