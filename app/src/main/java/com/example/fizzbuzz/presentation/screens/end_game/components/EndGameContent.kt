package com.example.fizzbuzz.presentation.screens.end_game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.presentation.screens.end_game.EndGameScreenState
import com.example.fizzbuzz.presentation.screens.home.components.MainActionButton
import com.example.fizzbuzz.ui.theme.BlackHanSans
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.PressStart2P

@Composable
fun EndGameContent(
    state: EndGameScreenState,
    navigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.end_game),
            fontFamily = PressStart2P,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.score) + ":",
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${state.score}",
            fontSize = 32.sp,
            fontFamily = BlackHanSans,
        )
        Spacer(modifier = Modifier.height(40.dp))
        MainActionButton(
            onClick = { navigateToHomeScreen() },
            text = stringResource(id = R.string.go_to_home_screen)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun EndGameContentPreview() {
    FizzBuzzTheme {
        EndGameContent(
            state = EndGameScreenState(score = 193),
            navigateToHomeScreen = {}
        )
    }
}