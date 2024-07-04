package com.example.feature_squad.presentation.squadfragment.typetwo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_squad.R
import com.example.feature_squad.presentation.squad.viewmodel.SquadViewModel
import com.example.feature_squad.presentation.util.LAKR_Purple_Dark
import com.example.feature_squad.presentation.util.LAKR_Purple_Light
import com.example.feature_squad.presentation.util.Purple

@Preview
@Composable
fun Preview() {
//    PlayerStaffTab()
}

@Composable
fun SquadScreen() {
    Scaffold (

    ) {

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SquadFragmentVerticalScroll (
    @DrawableRes homeSquadBackground: Int = R.drawable.lakr_squad_bg,
    firstNameTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Left
    ),
    lastNameTextStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left
    ),
    countryNameTextStyle: TextStyle = TextStyle(
        fontSize = 10.sp,
        color = Color.Gray,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Left
    ),
    selectedTabTextStyle: TextStyle = TextStyle (
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        color = Color.White,
    ),
    unselectedTabTextStyle: TextStyle = TextStyle (
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = Color.Gray,
    )

) {

    val viewModel: SquadViewModel = hiltViewModel()
    val squadStaffListing by viewModel.squadStaffListing.observeAsState()
    val playerFilterData = squadStaffListing?.playerFilteredData
    val supportStaffFilterData = squadStaffListing?.listOfStaff

    val pagerState = rememberPagerState { playerFilterData?.size ?: 0 }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabItem = playerFilterData?.map { it.title }
    var selectedTab by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column (modifier = Modifier.wrapContentSize()) {
        PlayerStaffTab(
            selectedTabIndex = selectedTab,
            selectedTabIndexU = { selectedTab = it }
        )

        if (selectedTab == 0)
            ScrollableTabRow (
                edgePadding = 0.dp,
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .wrapContentHeight(),
                containerColor = LAKR_Purple_Light,
                indicator = { }
            ) {
                tabItem?.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = tabItem,
                                style = if (selectedTabIndex == index) selectedTabTextStyle else unselectedTabTextStyle
                            )
                        },
                    )
                }

            }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
        ) { index ->

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                val list = playerFilterData?.get(index)?.playersList ?: emptyList()
                val staffList = supportStaffFilterData ?: emptyList()

                LazyColumn (
                    contentPadding = PaddingValues(16.dp)
                ) {

                    if (selectedTab == 0)
                        items(list) {
                            SquadFragmentTypeTwo (
                                playerImageModifier = Modifier,
                                firstNameTextStyle = firstNameTextStyle,
                                lastNameTextStyle = lastNameTextStyle,
                                countryNameTextStyle = countryNameTextStyle,
                                playerDetail = it
                            )
                    }
                    else
                        items(staffList) {
                            SquadStaffItemTypeTwo (
                                playerImageModifier = Modifier,
                                firstNameTextStyle = firstNameTextStyle,
                                lastNameTextStyle = lastNameTextStyle,
                                staffRoleTextStyle = countryNameTextStyle,
                                staffDetail = it
                            )
                        }
                }
            }
        }

    }
}

@Composable
fun PlayerStaffTab (
    selectedTabColor: Color = LAKR_Purple_Light,
    tabBackgroundColor: Color = Purple,
    selectedTabTextStyle: TextStyle = TextStyle(
        textAlign = TextAlign.Center,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    unselectedTabTextStyle: TextStyle = TextStyle(
        textAlign = TextAlign.Center,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    selectedTabIndex: Int,
    selectedTabIndexU: (Int) -> Unit,
//    content: @Composable RowScope.() -> Unit
) {

//    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Box (
        modifier = Modifier
            .background(color = LAKR_Purple_Dark),
    ){
        TabRow(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp),
            containerColor = LAKR_Purple_Dark,
            selectedTabIndex = selectedTabIndex,
            indicator = {}
        ) {
            Row (
                modifier = Modifier
                    .background(
                        color = tabBackgroundColor, shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 0.91.dp,
                        color = Color(0x4DFFFFFF),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Tab(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (selectedTabIndex == 0) selectedTabColor else tabBackgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    selected = selectedTabIndex == 0,
                    onClick = {
                        selectedTabIndexU(0)
                    },
                    text = {
                        Text(
                            text = "Player",
                            style = selectedTabTextStyle,
                        )
                    },
                )
                Tab(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (selectedTabIndex == 1) selectedTabColor else tabBackgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    selected = selectedTabIndex == 1,
                    onClick = {
                        selectedTabIndexU(1)
                    },
                    text = {
                        Text(
                            text = "Support Staff",
                            style = unselectedTabTextStyle,
                        )
                    },
                )
            }
        }
    }


}