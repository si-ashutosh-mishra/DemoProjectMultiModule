package com.example.feature_squad.presentation.squadfragment.typetwo

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_squad.R
import com.example.feature_squad.presentation.squad.viewmodel.SquadViewModel
import com.example.feature_squad.presentation.util.Purple

@Preview
@Composable
fun Preview() {
    PlayerStaffTab()
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
        fontSize = 14.sp,
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Left
    ),
) {

    val viewModel: SquadViewModel = hiltViewModel()
    val squadList by viewModel.player.observeAsState(initial = emptyList())
    val squadStaffListing by viewModel.squadStaffListing.observeAsState()
    val playerFilterData = squadStaffListing?.playerFilteredData

    val pagerState = rememberPagerState { playerFilterData?.size ?: 0 }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabItem = playerFilterData?.map { it.title }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column (modifier = Modifier.wrapContentSize()) {

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        ScrollableTabRow (
            edgePadding = 0.dp,
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.background(Color.Black).wrapContentHeight(),
            containerColor = Color.Blue,
            indicator = { tabPositions ->
                val currentTabPosition = tabPositions[pagerState.currentPage]
                val indicatorOffset by animateDpAsState(
                    targetValue = currentTabPosition.left,
                    animationSpec = spring(), label = ""
                )
                val indicatorWidth by animateDpAsState(
                    targetValue = currentTabPosition.width,
                    animationSpec = spring(), label = ""
                )

                Box(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.BottomStart)
                        .offset(x = indicatorOffset)
                        .width(indicatorWidth)
                        .height(4.dp)
                        .background(Color.Blue)
                )
            }
        ) {
//
            tabItem?.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = tabItem,
                            color = Color.White,
                            style = TextStyle(
                                textAlign = TextAlign.Center
                            ),
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
                LazyColumn (
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(list) {
                        SquadFragmentTypeTwo (
                            playerImageModifier = Modifier,
                            firstNameTextStyle = firstNameTextStyle,
                            lastNameTextStyle = lastNameTextStyle,
                            countryNameTextStyle = countryNameTextStyle,
                            playerDetail = it
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun PlayerStaffTab () {
    TabRow(
        modifier = Modifier,
        selectedTabIndex = 0
    ) {
        Row (
            modifier = Modifier
                .padding(10.dp)
                .background(
                    Purple,
                    shape = RoundedCornerShape(8.dp)
                )
        ){
            Tab(
                modifier = Modifier
                    .weight(1f),
                selected = true,
                onClick = {

                },
                text = {
                    Text(
                        text = "Player",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                    )
                },
            )
            Tab(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                selected = true,
                onClick = {

                },
                text = {
                    Text(
                        text = "Staff",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                    )
                },
            )
        }
    }
}