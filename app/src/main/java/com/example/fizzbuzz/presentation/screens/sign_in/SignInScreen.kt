package com.example.fizzbuzz.presentation.screens.sign_in

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
import com.example.fizzbuzz.presentation.screens.sign_in.components.SignInContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,

    ) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val onIntent: (SignInScreenIntent) -> Unit = { intent -> viewModel.processIntent(intent) }

    Scaffold { paddingValues ->
        SignInContent(
            state = state,
            onIntent = onIntent,
            navigateToSignUpScreen = navigateToSignUpScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }

    LaunchedEffect(key1 = state.isSignInSuccess) {
        if (state.isSignInSuccess) {
            navigateToHomeScreen()
        }
    }

    LaunchedEffect(key1 = state.signInErrorMessage) {
        if (state.signInErrorMessage != null) {
            Toast.makeText(
                context,
                context.getString(state.signInErrorMessage!!),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}