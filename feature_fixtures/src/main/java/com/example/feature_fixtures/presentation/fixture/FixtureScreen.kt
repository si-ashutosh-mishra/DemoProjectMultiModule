package com.example.feature_fixtures.presentation.fixture

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.presentation.fixture.typeone.LiveMatchCardTypeOne
import com.example.feature_fixtures.presentation.fixture.typeone.RecentMatchCardTypeOne
import com.example.feature_fixtures.presentation.fixture.typeone.UpcomingMatchCardTypeOne


@Composable
fun Fixture() {

    val viewModel: FixtureViewModel = hiltViewModel()

    val fixtureList by viewModel.fixture.observeAsState(initial = emptyList())

    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.getFixtureList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(fixtureList) {
            when(it?.eventState){
                EventState.RESULT ->{
                    RecentMatchCardTypeOne(
                        data = it,
                        sponsorLogo = R.drawable.ic_menu_fixture,
                        cardBackGroundColor = R.color.white,
                        matchNumberTextStyle = TextStyle(fontSize = 20.sp, color = Color.Red)
                    )
                }
                EventState.LIVE ->{
                    LiveMatchCardTypeOne(
                        data = it,
                        sponsorLogo = R.drawable.ic_menu_fixture,
                        cardBackGroundColor = R.color.white,
                        matchNumberTextStyle = TextStyle(fontSize = 20.sp, color = Color.Red)
                    )
                }
                EventState.UPCOMING ->{
                    UpcomingMatchCardTypeOne(
                        data = it,
                        sponsorLogo = R.drawable.ic_menu_fixture,
                        cardBackGroundColor = R.color.white,
                        matchNumberTextStyle = TextStyle(fontSize = 20.sp, color = Color.Red)
                    )
                }
                else ->{

                }
            }

        }
    }


}