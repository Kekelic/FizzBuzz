package com.example.fizzbuzz.presentation.screens.game.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.presentation.screens.game.GameScreenIntent
import com.example.fizzbuzz.presentation.screens.game.GameScreenState
import com.example.fizzbuzz.ui.theme.BlackHanSans
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun GameContent(
    state: GameScreenState,
    onIntent: (GameScreenIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${state.number}",
            fontFamily = BlackHanSans,
            fontSize = if (state.number < 999) 158.sp else 96.sp,
            lineHeight = if (state.number < 999) 118.sp else 76.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))
        GameControllerButton(
            onClick = { onIntent(GameScreenIntent.FizzBuzz) },
            text = stringResource(id = R.string.fizzbuzz)
        )
        Spacer(modifier = Modifier.height(1.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            GameControllerButton(
                onClick = { onIntent(GameScreenIntent.Fizz) },
                text = stringResource(id = R.string.fizz)
            )
            GameControllerButton(
                onClick = { onIntent(GameScreenIntent.Buzz) },
                text = stringResource(id = R.string.buzz)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        GameControllerButton(
            onClick = { onIntent(GameScreenIntent.Next) },
            text = stringResource(id = R.string.next)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GameContentPreview() {
    FizzBuzzTheme {
        GameContent(
            state = GameScreenState(),
            onIntent = {}
        )
    }
}