package com.example.satndings.presentation.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.satndings.R

@Composable
fun StandingToolbar(
    onBackClick: () -> Unit,
    titleBarIconTintColor: Int,
    toolBarColor: Int,
    toolBarTitle: String,
    toolBarTitleTextStyle: TextStyle,
    showBack: Boolean = true,
    showFilter: Boolean = true
) {
    Box(
        modifier = Modifier
            .background(color = colorResource(id = toolBarColor))
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
        ) {
            if (showBack) {
                IconButton(onClick = { onBackClick.invoke() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = colorResource(id = titleBarIconTintColor)
                    )
                }
            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth()
        ) {
            Text(
                style = toolBarTitleTextStyle, text = toolBarTitle.uppercase(), maxLines = 2
            )
        }
        Column(
            horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()
        ) {
            if (showFilter) {
                IconButton(onClick = { onBackClick.invoke() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_standings_filter),
                        contentDescription = null,
                        tint = colorResource(id = titleBarIconTintColor)
                    )
                }
            }

        }
    }
}