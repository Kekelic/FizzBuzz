package com.example.fizzbuzz.presentation.screens.sign_up

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
import com.example.fizzbuzz.presentation.screens.sign_up.components.SignUpContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignInScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val onIntent: (SignUpScreenIntent) -> Unit = { intent -> viewModel.processIntent(intent) }

    Scaffold { paddingValues ->
        SignUpContent(
            state = state,
            onIntent = onIntent,
            navigateToSignInScreen = navigateToSignInScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }

    LaunchedEffect(key1 = state.isSignUpSuccess) {
        if (state.isSignUpSuccess) {
            navigateToHomeScreen()
        }
    }

    LaunchedEffect(key1 = state.signUpErrorMessage) {
        if (state.signUpErrorMessage != null) {
            Toast.makeText(
                context,
                context.getString(state.signUpErrorMessage!!),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
