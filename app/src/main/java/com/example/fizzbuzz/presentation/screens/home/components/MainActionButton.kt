package com.example.fizzbuzz.presentation.screens.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    buttonColor: Color = Color.DarkGray
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(7.dp)
        )
    }
}