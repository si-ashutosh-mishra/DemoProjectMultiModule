package com.example.standing.presentation.standing

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
import com.example.standing.presentation.theme.White20

@Composable
fun StandingsScreen(
    navController: NavController,
    toolBarBGColor: Color = Color.Black,
    titleBarIconTintColor: Color = Color.White,
    toolBarTitleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    ),
    toolBarTitle: String = "Point Table",
    @DrawableRes mainBg: Int = R.drawable.kkr_bg_standings,
    titleBarBGColor: Color = Color.Blue,
    titleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    valueTextStyle: TextStyle = TextStyle(
        color = DarkBlue,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    ),
    teamPosStyle: TextStyle = TextStyle(
        color = DarkBlue,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    teamNameStyle: TextStyle = TextStyle(
        color = DarkBlue,
        textAlign = TextAlign.Start,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    ),

    leftViewBgColor: Color = Color.Yellow,
    qualifiedBGColor: Color = Color.Red,
    rightViewBgColor: Color = Color.White,
    selectedTeamBGColor: Color = Color.Blue,
    circularTeamBGColor: Color = White20,
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


