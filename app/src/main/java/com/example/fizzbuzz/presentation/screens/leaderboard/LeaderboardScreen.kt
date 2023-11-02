package com.example.fizzbuzz.presentation.screens.leaderboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fizzbuzz.presentation.screens.components.CustomScreenProgressIndicator
import com.example.fizzbuzz.presentation.screens.leaderboard.components.LeaderboardContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    viewModel: LeaderboardViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    Scaffold { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CustomScreenProgressIndicator()
            }
        } else if (state.errorMessage.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage,
                )
            }
        } else {
            LeaderboardContent(
                state = state,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}