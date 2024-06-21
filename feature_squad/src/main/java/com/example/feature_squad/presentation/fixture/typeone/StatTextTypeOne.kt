package com.example.feature_squad.presentation.fixture.typeone

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun StatText(
    value : String,
    heading : String,
    valueStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    headingStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    )
){
    Column {
        Text(text = value,
            style = valueStyle)
        Text(text = heading,
           style = headingStyle)
    }
}