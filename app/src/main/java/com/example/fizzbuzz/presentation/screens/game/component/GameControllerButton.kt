package com.example.fizzbuzz.presentation.screens.game.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.ui.theme.DarkYellow

@Composable
fun GameControllerButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .height(53.dp)
            .width(106.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
        ),
        shape = CircleShape
    ) {
        Text(
            text = text,
            color = DarkYellow,
        )
    }
}