package com.example.feature_squad.presentation.squadfragment.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
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
import com.example.feature_squad.presentation.util.LAKR_Purple_Light
import com.example.feature_squad.presentation.util.Purple

@Preview
@Composable
fun Preview() {
    SquadTypeOneScreen()
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SquadTypeOneScreen (
    @DrawableRes homeSquadBackground: Int = R.drawable.lakr_squad_bg,
    firstNameTextStyle: TextStyle = TextStyle(
        fontSize = 15.sp,
        color = Color.Black,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    ),
    lastNameTextStyle: TextStyle = TextStyle(
        fontSize = 17.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
) {

//    val viewModel: SquadViewModel = hiltViewModel()
//    val squadStaffListing by viewModel.squadStaffListing.observeAsState()
//    val playerFilterData = squadStaffListing?.playerFilteredData
//    val supportStaffFilterData = squadStaffListing?.listOfStaff

    Box (modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .clip(RoundedCornerShape(5.dp))
        .background(Color.White)){

        Box(modifier = Modifier.align(Alignment.TopEnd)
            .padding(20.dp)
            .background(Color.Blue)
            .padding(10.dp)){

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
        fontSize = 20.sp
    ),
    unselectedTabTextStyle: TextStyle = TextStyle(
        textAlign = TextAlign.Center,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
//    selectedTabIndex: Int,
//    content: @Composable RowScope.() -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(
        modifier = Modifier,
        selectedTabIndex = 0
    ) {
        Row (
            modifier = Modifier
                .padding(10.dp)
                .background(
                    color = tabBackgroundColor, shape = RoundedCornerShape(8.dp)
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
                    selectedTabIndex = 0
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
                    selectedTabIndex = 1
                },
                text = {
                    Text(
                        text = "Staff",
                        style = unselectedTabTextStyle,
                    )
                },
            )
        }
    }

}