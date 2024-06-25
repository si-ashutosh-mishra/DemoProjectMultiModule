package com.example.standing.presentation.standing

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.standing.R
import com.example.standing.presentation.theme.DarkBlue
import com.example.standing.presentation.theme.interBold
import com.example.standing.presentation.theme.interRegular

@Composable
fun StandingsScreen(
    navController: NavController,
    @ColorRes toolBarBGColor: Int = R.color.black,
    @ColorRes titleBarIconTintColor: Int = R.color.white,
    toolBarTitleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = interBold,
        fontWeight = FontWeight.Bold,
    ),
    toolBarTitle: String = stringResource(R.string.points_table),
    @DrawableRes mainBg: Int = R.drawable.kkr_bg_standings,
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
    topRadius: Dp = 20.dp,
    bottomRadius: Dp = 20.dp,
    currentTeamID: Int? = null,
    isShowForm: Boolean = true,
    swapPosition: Int? = null,
    isSwapRequired: Boolean = false,
    requiredTeamCount: Int? = null,
    showBack: Boolean = true,
    showFilter: Boolean = true
) {

    val viewModel: StandingViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadStanding(
            isShowForm = isShowForm,
            isSwapRequired = isSwapRequired,
            requiredTeamCount = requiredTeamCount,
            swapPosition = swapPosition,
            currentTeamId = currentTeamID
        )
    }

    val pointsTableList by viewModel.standingLiveData.observeAsState(initial = emptyList())


    Column {
        StandingToolbar(
            onBackClick = { navController.popBackStack() },
            onFilterClick = { },
            titleBarIconTintColor = titleBarIconTintColor,
            toolBarColor = toolBarBGColor,
            toolBarTitle = toolBarTitle,
            toolBarTitleTextStyle = toolBarTitleTextStyle,
            showBack = showBack,
            showFilter = showFilter
        )
        Box {
            SetScreenBg(image = mainBg)
            if (!pointsTableList.isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 15.dp, vertical = 30.dp
                        )
                        .verticalScroll(rememberScrollState())
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


@Composable
fun SetScreenBg(image: Int) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}


