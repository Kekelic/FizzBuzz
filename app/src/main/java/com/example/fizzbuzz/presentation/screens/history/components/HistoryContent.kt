package com.example.fizzbuzz.presentation.screens.history.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
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
import com.example.fizzbuzz.presentation.screens.history.HistoryScreenState
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.PressStart2P

@Composable
fun HistoryContent(
    state: HistoryScreenState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.your_local_history),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = PressStart2P,
                fontSize = 28.sp,
                lineHeight = 36.sp
            )
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (state.scores.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.history_is_empty))
                }
            }
        } else {
            itemsIndexed(state.scores) { index, historyScore ->
                HistoryScoreItem(historyScore = historyScore, place = index + 1)
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryContentPreview() {
    FizzBuzzTheme {
        HistoryContent(
            state = HistoryScreenState(scores = emptyList())
        )
    }
}