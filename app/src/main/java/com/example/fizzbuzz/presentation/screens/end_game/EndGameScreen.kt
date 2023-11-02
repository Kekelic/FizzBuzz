package com.example.fizzbuzz.presentation.screens.end_game

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fizzbuzz.presentation.screens.end_game.components.EndGameContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndGameScreen(
    viewModel: EndGameViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    Scaffold { paddingValues ->
        EndGameContent(
            state = state,
            navigateToHomeScreen = navigateToHomeScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }
}