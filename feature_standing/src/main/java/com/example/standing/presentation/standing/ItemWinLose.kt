package com.example.standing.presentation.standing


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
import com.example.standing.presentation.theme.DarkBlue
import com.example.standing.presentation.theme.Green
import com.example.standing.presentation.theme.Red

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
            textAlign = TextAlign.Center
        )
    }
}