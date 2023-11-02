package com.example.fizzbuzz.presentation.screens.leaderboard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.domain.model.ScoreResponse
import com.example.fizzbuzz.presentation.screens.components.PlaceNumber
import com.example.fizzbuzz.ui.theme.BlackHanSans
import com.example.fizzbuzz.ui.theme.DarkYellow

@Composable
fun LeaderboardItem(
    score: ScoreResponse,
    place: Int,
) {
    Card(
        modifier = Modifier.padding(vertical = 3.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlaceNumber(
                value = place,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = score.username!!,
                modifier = Modifier.weight(5f),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = DarkYellow,
                textAlign = TextAlign.Center,
                letterSpacing = 2.sp
            )
            Text(
                text = score.value.toString(),
                modifier = Modifier.weight(3f),
                fontSize = 32.sp,
                fontFamily = BlackHanSans,
                textAlign = TextAlign.Center
            )
        }
    }
}