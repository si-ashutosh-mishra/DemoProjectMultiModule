package com.example.feature_fixtures.ui.fixture

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_fixtures.R


@Composable
fun Fixture() {

    val viewModel: FixtureViewModel = hiltViewModel()
    LaunchedEffect(
        key1 = Unit
    ) {
        //viewModel.getFixtureList()
    }

    // Column Composable,
    Column(
        modifier = Modifier.fillMaxSize(),
        // Parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Image(
            painter = painterResource(id = R.drawable.ic_menu_fixture),
            contentDescription = null,
        )
        Button(onClick = { viewModel.getFixtureList() }) {
            Text(
                text = "Get Fixture", style = TextStyle(
                    fontSize = 13.08.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }
        // Text to Display the current Screen
    }
}