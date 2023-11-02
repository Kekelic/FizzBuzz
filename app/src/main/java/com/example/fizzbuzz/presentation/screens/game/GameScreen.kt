package com.example.fizzbuzz.presentation.screens.game

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fizzbuzz.presentation.screens.components.CustomScreenProgressIndicator
import com.example.fizzbuzz.presentation.screens.game.component.GameContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    navigateToEndGameScreen: (Int) -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val onIntent: (GameScreenIntent) -> Unit = { intent -> viewModel.processIntent(intent) }

    Scaffold { paddingValues ->
        if (state.isSaveScoreLoading || state.scoreResultMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CustomScreenProgressIndicator()
            }
        } else {
            GameContent(
                state = state,
                onIntent = onIntent,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }

    LaunchedEffect(key1 = state.scoreResultMessage) {
        if (state.scoreResultMessage != null) {
            Toast.makeText(
                context,
                context.getString(state.scoreResultMessage!!),
                Toast.LENGTH_LONG
            ).show()
            navigateToEndGameScreen(state.number)
        }
    }

    LaunchedEffect(key1 = state.saveScoreErrorMessage) {
        if (state.saveScoreErrorMessage.isNotEmpty()) {
            Toast.makeText(context, state.saveScoreErrorMessage, Toast.LENGTH_LONG).show()
            navigateToEndGameScreen(state.number)
        }
    }
}