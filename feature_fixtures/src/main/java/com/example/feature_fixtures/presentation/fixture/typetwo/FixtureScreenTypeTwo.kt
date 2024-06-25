package com.example.feature_fixtures.presentation.fixture.typetwo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.presentation.fixture.viewmodel.FixtureViewModel
import com.example.feature_fixtures.presentation.fixture.LifeCycleObserver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixtureScreenTypeTwo(
    navController: NavController,
    isSponsorLogoRequired: Boolean = false,
    @DrawableRes sponsorLogo: Int? = null,
    @DrawableRes liveLogo: Int = R.drawable.ic_live,
    @DrawableRes recentLogo: Int = R.drawable.ic_recent,
    @DrawableRes upcomingLogo: Int = R.drawable.ic_upcoming,
    matchNumberTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    teamNameTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    matchStatusTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    timeStampTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    highLightedScoreStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    highLightedOverStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    generalScoreStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    generalOverStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes matchCardBackGroundImage: Int? = null,
    @ColorRes cardBackGroundColor: Int? = null,
    @ColorRes cardBorderColor: Int = R.color.black,
    teamId: String? = null,
    onClickItem: (name: String?) -> Unit
) {

    val viewModel: FixtureViewModel = hiltViewModel()

    val fixtureList by viewModel.fixture.observeAsState(initial = emptyList())

    LifeCycleObserver(fetchData = {
        viewModel.cancelApiCoroutine()
        viewModel.getFixtureList(teamId)
    }) {
        viewModel.cancelApiCoroutine()
    }

    Column {
        TopAppBar(title = {
            Text(text = "Fixtures")
        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(fixtureList) {
                when (it?.eventState) {
                    EventState.RESULT -> {
                        RecentMatchCardTypeTwo(
                            data = it,
                            isSponsorLogoRequired = isSponsorLogoRequired,
                            recentLogo = recentLogo,
                            sponsorLogo = sponsorLogo,
                            matchNumberTextStyle = matchNumberTextStyle,
                            teamNameTextStyle = teamNameTextStyle,
                            matchStatusTextStyle = matchStatusTextStyle,
                            timeStampTextStyle = timeStampTextStyle,
                            highLightedScoreStyle = highLightedScoreStyle,
                            highLightedOverStyle = highLightedOverStyle,
                            generalScoreStyle = generalScoreStyle,
                            generalOverStyle = generalOverStyle,
                            matchCardBackGroundImage = matchCardBackGroundImage,
                            cardBackGroundColor = cardBackGroundColor,
                            cardBorderColor = cardBorderColor
                        )
                    }

                    EventState.LIVE -> {
                        LiveMatchCardTypeTwo(
                            data = it,
                            isSponsorLogoRequired = isSponsorLogoRequired,
                            liveLogo = liveLogo,
                            sponsorLogo = sponsorLogo,
                            matchNumberTextStyle = matchNumberTextStyle,
                            teamNameTextStyle = teamNameTextStyle,
                            matchStatusTextStyle = matchStatusTextStyle,
                            timeStampTextStyle = timeStampTextStyle,
                            highLightedScoreStyle = highLightedScoreStyle,
                            highLightedOverStyle = highLightedOverStyle,
                            generalScoreStyle = generalScoreStyle,
                            generalOverStyle = generalOverStyle,
                            matchCardBackGroundImage = matchCardBackGroundImage,
                            cardBackGroundColor = cardBackGroundColor,
                            cardBorderColor = cardBorderColor
                        )
                    }

                    EventState.UPCOMING -> {
                        UpcomingMatchCardTypeTwo(
                            data = it,
                            isSponsorLogoRequired = isSponsorLogoRequired,
                            upcomingLogo = upcomingLogo,
                            sponsorLogo = sponsorLogo,
                            matchNumberTextStyle = matchNumberTextStyle,
                            matchStatusTextStyle = matchStatusTextStyle,
                            timeStampTextStyle = timeStampTextStyle,
                            matchCardBackGroundImage = matchCardBackGroundImage,
                            cardBackGroundColor = cardBackGroundColor,
                            cardBorderColor = cardBorderColor
                        ) {
                            onClickItem(it)
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}