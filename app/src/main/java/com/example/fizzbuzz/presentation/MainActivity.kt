package com.example.fizzbuzz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.fizzbuzz.navigation.NavigationGraph
import com.example.fizzbuzz.navigation.Screen
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FizzBuzzTheme {
                NavigationGraph(
                    startDestination = if (viewModel.currentUser != null) Screen.Home.route
                    else Screen.SignIn.route
                )
            }
        }
    }

}


