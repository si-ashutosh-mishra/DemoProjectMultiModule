package com.example.feature_fixtures.presentation.fixture.typeone

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.presentation.fixture.FixtureViewModel


@Composable
fun FixtureTypeOne(
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
    matchCount:Int = 3,
    onClickItem:(name:String?) ->Unit
) {

    val viewModel: FixtureViewModel = hiltViewModel()

    val fixtureList by viewModel.fixture.observeAsState(initial = emptyList())

    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.getFixtureList(matchCount)
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(fixtureList) {
            when(it?.eventState){
                EventState.RESULT ->{
                    RecentMatchCardTypeOne(
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
                EventState.LIVE ->{
                    LiveMatchCardTypeOne(
                        data = it,
                        isSponsorLogoRequired=isSponsorLogoRequired,
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
                EventState.UPCOMING ->{
                    UpcomingMatchCardTypeOne(
                        data = it,
                        isSponsorLogoRequired=isSponsorLogoRequired,
                        upcomingLogo = upcomingLogo,
                        sponsorLogo = sponsorLogo,
                        matchNumberTextStyle = matchNumberTextStyle,
                        teamNameTextStyle = teamNameTextStyle,
                        matchStatusTextStyle = matchStatusTextStyle,
                        timeStampTextStyle = timeStampTextStyle,
                        matchCardBackGroundImage = matchCardBackGroundImage,
                        cardBackGroundColor = cardBackGroundColor,
                        cardBorderColor = cardBorderColor
                    ){
                        onClickItem(it)
                    }
                }
                else ->{}
            }
        }
    }


}