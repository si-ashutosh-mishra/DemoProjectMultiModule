package com.example.feature_fixtures.presentation.fixture

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_fixtures.R
import com.example.feature_fixtures.presentation.fixture.typeone.LiveMatchCardTypeOne
import com.example.feature_fixtures.presentation.fixture.typeone.RecentMatchCardTypeOne
import com.example.feature_fixtures.presentation.fixture.typeone.UpcomingMatchCardTypeOne
import dagger.Lazy


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
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            // on below line we are populating
            // items for listview.
            items(20) { item ->
                // on below line we are specifying ui for each item of list view.
                // we are specifying a simple text for each item of our list view.
                //UpcomingMatchCardTypeOne(sponsorLogo = R.drawable.ic_menu_fixture)
                // on below line we are specifying
                // divider for each list item
                Divider()
            }
        }

        // Text to Display the current Screen
    }
}