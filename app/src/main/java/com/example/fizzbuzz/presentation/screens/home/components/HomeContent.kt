package com.example.fizzbuzz.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.presentation.screens.home.HomeScreenIntent
import com.example.fizzbuzz.presentation.screens.home.HomeScreenState
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.PressStart2P

@Composable
fun HomeContent(
    state: HomeScreenState,
    onIntent: (HomeScreenIntent) -> Unit,
    navigateToGameScreen: () -> Unit,
    navigateToLeaderboardScreen: () -> Unit,
    navigateToHistoryScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Yellow.copy(alpha = 0.4f))
                .padding(12.dp),
            painter = painterResource(id = R.drawable.light_bulb),
            contentDescription = stringResource(id = R.string.light_bulb),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 34.sp,
            fontFamily = PressStart2P,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.welcome) + " " + state.username,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        MainActionButton(
            onClick = { navigateToGameScreen() },
            modifier = Modifier.fillMaxWidth(0.7f),
            text = stringResource(id = R.string.play)
        )
        Spacer(modifier = Modifier.height(30.dp))
        MainActionButton(
            onClick = { navigateToLeaderboardScreen() },
            modifier = Modifier.fillMaxWidth(0.7f),
            text = stringResource(id = R.string.leaderboard)
        )
        Spacer(modifier = Modifier.height(30.dp))
        MainActionButton(
            onClick = { navigateToHistoryScreen() },
            modifier = Modifier.fillMaxWidth(0.7f),
            text = stringResource(id = R.string.history)
        )
        Spacer(modifier = Modifier.height(30.dp))
        MainActionButton(
            onClick = { onIntent(HomeScreenIntent.SignOut) },
            modifier = Modifier.fillMaxWidth(0.7f),
            text = stringResource(id = R.string.sign_out),
            buttonColor = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    FizzBuzzTheme {
        HomeContent(
            state = HomeScreenState(),
            onIntent = {},
            navigateToGameScreen = {},
            navigateToLeaderboardScreen = {},
            navigateToHistoryScreen = {}
        )
    }
}