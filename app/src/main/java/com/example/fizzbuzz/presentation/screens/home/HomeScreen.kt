package com.example.fizzbuzz.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fizzbuzz.presentation.screens.home.components.HomeContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToGameScreen: () -> Unit,
    navigateToLeaderboardScreen: () -> Unit,
    navigateToHistoryScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val onIntent: (HomeScreenIntent) -> Unit = { intent -> viewModel.processIntent(intent) }

    Scaffold { paddingValues ->
        HomeContent(
            state = state,
            onIntent = onIntent,
            navigateToGameScreen = navigateToGameScreen,
            navigateToLeaderboardScreen = navigateToLeaderboardScreen,
            navigateToHistoryScreen = navigateToHistoryScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }

    LaunchedEffect(key1 = state.isSignOutSuccess) {
        if (state.isSignOutSuccess) {
            navigateToSignInScreen()
        }
    }

    LaunchedEffect(key1 = state.signOutErrorMessage) {
        if (state.signOutErrorMessage.isNotEmpty()) {
            Toast.makeText(context, state.signOutErrorMessage, Toast.LENGTH_LONG).show()
        }
    }
}