package com.example.fizzbuzz.presentation.screens.history

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fizzbuzz.presentation.screens.history.components.HistoryContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Scaffold { paddingValues ->
        HistoryContent(
            state = state,
            modifier = Modifier.padding(paddingValues)
        )
    }
}