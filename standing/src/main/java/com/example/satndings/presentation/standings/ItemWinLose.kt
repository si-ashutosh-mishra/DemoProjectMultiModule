package com.example.satndings.presentation.standings


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.satndings.presentation.theme.DarkBlue
import com.example.satndings.presentation.theme.Green
import com.example.satndings.presentation.theme.Red
import com.example.satndings.presentation.theme.interBold

@Composable
fun ItemWinLose(winLoss: String?) {
    winLoss ?: return
    Box(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .size(20.dp)
            .clip(shape = CircleShape)
            .background(
                when (winLoss) {
                    "L" -> {
                        Red
                    }

                    "W" -> {
                        Green
                    }

                    "D" -> {
                        DarkBlue
                    }

                    else -> {
                        DarkBlue
                    }
                }
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = winLoss,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = interBold,
            textAlign = TextAlign.Center
        )
    }
}