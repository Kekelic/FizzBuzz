package com.example.fizzbuzz.presentation.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButtonProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(33.dp),
        color = Color.LightGray
    )
}