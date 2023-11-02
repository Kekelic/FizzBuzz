package com.example.fizzbuzz.presentation.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun PlaceNumber(value: Int, modifier: Modifier) {
    val superscript = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 8.sp
    )

    Text(
        text = buildAnnotatedString {
            append(value.toString())
            withStyle(superscript) {
                append(getPlaceInitials(value))
            }
        },
        modifier = modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

private fun getPlaceInitials(value: Int): String {
    return when (value) {
        1 -> "ST"
        2 -> "ND"
        3 -> "RD"
        else -> "TH"
    }
}