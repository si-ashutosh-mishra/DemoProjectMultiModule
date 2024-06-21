package com.example.standing.presentation.standinghome

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.standing.R
import com.example.standing.presentation.standing.LeftDataSection
import com.example.standing.presentation.standing.RightDataSection
import com.example.standing.presentation.standing.StandingViewModel
import com.example.standing.presentation.theme.DarkBlue
import com.example.standing.presentation.theme.interBold
import com.example.standing.presentation.theme.interRegular


@Composable
fun StandingHome(
    title: String = "Standing",
    standingHeadingStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center, fontSize = 16.sp, fontFamily = interBold
    ),

    @ColorRes widgetBackGroundColor: Int? = null,
    @DrawableRes widgetBackGroundImage: Int? = R.drawable.kkr_bg_standings,
    @DrawableRes viewMoreLogo: Int = R.drawable.ic_view_more,
    onViewMoreClick: () -> Unit,
    topRadius: Dp = 20.dp,
    bottomRadius: Dp = 20.dp,
    @ColorRes titleBarBGColor: Int = R.color.kkr_toolbar,
    titleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = interBold,
        fontWeight = FontWeight.Normal,
    ),
    valueTextStyle: TextStyle = TextStyle(
        color = DarkBlue,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontFamily = interRegular,
        fontWeight = FontWeight.Normal,
    ),
    teamPosStyle: TextStyle = TextStyle(
        color = DarkBlue,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontFamily = interBold,
        fontWeight = FontWeight.Normal
    ),
    teamNameStyle: TextStyle = TextStyle(
        color = DarkBlue,
        textAlign = TextAlign.Start,
        fontSize = 12.sp,
        fontFamily = interBold,
        fontWeight = FontWeight.Normal,
    ),
    @ColorRes leftViewBgColor: Int = R.color.golden_dark_50,
    @ColorRes qualifiedBGColor: Int = R.color.red,
    @ColorRes rightViewBgColor: Int = R.color.white,
    @ColorRes selectedTeamBGColor: Int = R.color.kkr_toolbar_50,
    @ColorRes circularTeamBGColor: Int = R.color.white_20,
    currentTeamID: Int? = null,
    requiredTeamCount: Int? = null,
    showMore: Boolean = true,
    showTitle: Boolean = true,
    //pointsTableList: List<IPLStandings>? = null,
) {

    val viewModel: StandingViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadStanding(requiredTeamCount = requiredTeamCount)

    }
    val pointsTableList by viewModel.standingLiveData.observeAsState(initial = emptyList())

    if (!pointsTableList.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .background(if (widgetBackGroundColor != null) colorResource(id = widgetBackGroundColor) else Color.Transparent)
                .fillMaxWidth()
        ) {
            if (widgetBackGroundImage != null) {
                Image(
                    painterResource(id = widgetBackGroundImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds, // or some other scale
                    modifier = Modifier.matchParentSize()
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    if (showTitle) Text(text = title.uppercase(), style = standingHeadingStyle)
                    if (showMore) {
                        Image(painterResource(viewMoreLogo),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(20.dp)
                                .background(Color.Transparent)
                                .clickable {
                                    onViewMoreClick()
                                })
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp
                        )

                ) {
                    LeftDataSection(
                        modifier = Modifier.weight(3.5f),
                        firstItem = pointsTableList.firstOrNull(),
                        list = pointsTableList.drop(1),
                        topRadius = topRadius,
                        bottomRadius = bottomRadius,
                        titleBarBGColor = titleBarBGColor,
                        titleTextStyle = titleTextStyle,
                        teamPosStyle = teamPosStyle,
                        teamNameStyle = teamNameStyle,
                        leftViewBgColor = leftViewBgColor,
                        selectedTeamBGColor = selectedTeamBGColor,
                        circularTeamBGColor = circularTeamBGColor,
                        qualifiedBGColor = qualifiedBGColor,
                        currentTeamID = currentTeamID,
                    )
                    RightDataSection(
                        modifier = Modifier.weight(6.5f),
                        currentTeamID = currentTeamID,
                        list = pointsTableList,
                        topRadius = topRadius,
                        bottomRadius = bottomRadius,
                        titleBarBGColor = titleBarBGColor,
                        rightViewBgColor = rightViewBgColor,
                        selectedTeamBGColor = selectedTeamBGColor,
                        titleTextStyle = titleTextStyle,
                        valueTextStyle = valueTextStyle
                    )
                }
            }

        }
    }

}