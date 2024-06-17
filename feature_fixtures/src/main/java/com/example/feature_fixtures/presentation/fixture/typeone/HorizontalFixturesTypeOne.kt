package com.example.feature_fixtures.presentation.fixture.typeone

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.presentation.fixture.PageIndicator

@ExperimentalFoundationApi
@Composable
fun FixturesHorizontalScroll(
    list: List<IPLMatch?>, isSponsorLogoRequired: Boolean = false,
    @DrawableRes sponsorLogo: Int? = null,
    @DrawableRes liveLogo: Int = R.drawable.ic_live,
    @DrawableRes recentLogo: Int = R.drawable.ic_recent,
    @DrawableRes upcomingLogo: Int = R.drawable.ic_upcoming,
    @DrawableRes viewMoreLogo: Int = R.drawable.ic_view_more,
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
    fixtureHeadingStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes matchCardBackGroundImage: Int? = null,
    @ColorRes cardBackGroundColor: Int? = null,
    @ColorRes cardBorderColor: Int = R.color.black,
    @ColorRes widgetBackGroundColor: Int? = null,
    @DrawableRes widgetBackGroundImage: Int? = null,
    onClickItem: (name: String?) -> Unit,
    onViewMoreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(if (widgetBackGroundColor != null) colorResource(id = widgetBackGroundColor) else Color.Transparent)
            .fillMaxWidth()
    ) {
        val pagerState = rememberPagerState { list.size }
        if (widgetBackGroundImage != null) {
            Image(
                painterResource(id = widgetBackGroundImage),
                contentDescription = "",
                contentScale = ContentScale.FillBounds, // or some other scale
                modifier = Modifier.matchParentSize()
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Fixtures & Results", style = fixtureHeadingStyle)
                Image(
                    painterResource(viewMoreLogo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(15.dp)
                        .background(Color.Transparent)
                        .clickable {
                            onViewMoreClick()
                        }
                )
            }
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 15.dp),
                pageSpacing = 0.dp
            ) {
                val data = list[it]
                when (data?.eventState) {
                    EventState.RESULT -> {
                        RecentMatchCardTypeOne(
                            data = data,
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
                        LiveMatchCardTypeOne(
                            data = data,
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
                        UpcomingMatchCardTypeOne(
                            data = data,
                            isSponsorLogoRequired = isSponsorLogoRequired,
                            upcomingLogo = upcomingLogo,
                            sponsorLogo = sponsorLogo,
                            matchNumberTextStyle = matchNumberTextStyle,
                            teamNameTextStyle = teamNameTextStyle,
                            matchStatusTextStyle = matchStatusTextStyle,
                            timeStampTextStyle = timeStampTextStyle,
                            matchCardBackGroundImage = matchCardBackGroundImage,
                            cardBackGroundColor = cardBackGroundColor,
                            cardBorderColor = cardBorderColor
                        ) {
                            onClickItem(it)
                        }
                    }

                    else -> {

                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            PageIndicator(pagerState = pagerState, pageCount = list.size)
        }
    }


}