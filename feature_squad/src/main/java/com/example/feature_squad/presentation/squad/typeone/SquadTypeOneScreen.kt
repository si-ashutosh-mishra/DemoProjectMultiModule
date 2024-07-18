package com.example.feature_squad.presentation.squad.typeone


import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.decode.ImageSource
import com.example.feature_squad.R
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.presentation.common.SquadToolbar
import com.example.feature_squad.presentation.squadhome.viewmodel.SquadViewModel
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
    navcontroller : NavController? = null,
    toolBarBGColor: Color = Color.Black,
    titleBarIconTintColor: Color = Color.White,
    toolBarTitleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    ),
    toolBarTitle: String = "Point Table",
    modifier: Modifier = Modifier
        .width(220.dp)
        .background(Color.Transparent)
        .fillMaxWidth(),
    @DrawableRes homeSquadBackground: Int = R.drawable.bg_player_home,
    playerNameBackgroundModifier : Modifier = Modifier
        .padding(bottom = 10.dp)
        .background(Color(0xFFF2C029)),
    bottomBackgroundModifier: Modifier = Modifier.background(Color(0xFF3A225D)),
    firstNameTextStyle: TextStyle = TextStyle(
        fontSize = 15.sp,
        color = Color(0xFF3A225D),
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    ),
    lastNameTextStyle: TextStyle = TextStyle(
        fontSize = 17.sp,
        color = Color(0xFF3A225D),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerRoleValueTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerRoleHeadingTextStyle: TextStyle = TextStyle(
        fontSize = 10.sp,
        color = Color.White,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center
    ),
    skillTextStyle : TextStyle = TextStyle(color = Color.White,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold
    ),
    roleBottomTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.White,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    ),
    skillHeadingTextStyle : TextStyle = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Medium)
) {

    val viewModel: SquadViewModel = hiltViewModel()
    val squadStaffListing by viewModel.squadStaffListing.observeAsState()
    val playerFilterData = squadStaffListing?.playerFilteredData
    val supportStaffFilterData = squadStaffListing?.listOfStaff
    Column (modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(Color.White)){
        SquadToolbar(onBackClick = {navcontroller?.popBackStack()},
            toolBarTitle = toolBarTitle,
            toolBarColor = toolBarBGColor,
            toolBarTitleTextStyle = toolBarTitleTextStyle,
            titleBarIconTintColor = titleBarIconTintColor)
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for(playerList in playerFilterData.orEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.Center) {
                        Divider(color = Color.Gray, thickness = 2.dp)
                        Text(modifier = Modifier
                            .padding(0.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xFF3A225D))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                            text = playerList.title.toUpperCase(),
                            style = skillHeadingTextStyle
                        )
                    }
                }
                items(playerList.playersList.size) {
                    SquadFragmentTypeOne(homeSquadBackground = homeSquadBackground,
                        modifier = modifier,
                        playerNameBackgroundModifier = playerNameBackgroundModifier,
                        bottomBackgroundModifier = bottomBackgroundModifier,
                        firstNameTextStyle = firstNameTextStyle,
                        lastNameTextStyle = lastNameTextStyle,
                        playerRoleValueTextStyle = playerRoleValueTextStyle,
                        playerRoleHeadingTextStyle = playerRoleHeadingTextStyle,
                        skillTextStyle = skillTextStyle,
                        playerDetail = playerList.playersList[it])
                }
            }
            (supportStaffFilterData?.size)?.let { it ->
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        contentAlignment = Alignment.Center) {
                        Divider(color = Color.Gray, thickness = 2.dp)
                        Text(modifier = Modifier
                            .padding(0.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xFF3A225D))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                            text = "Support Staff".toUpperCase(),
                            style = skillHeadingTextStyle
                        )
                    }
                }
                items(it){
                    StaffFragmentTypeOne(
                        modifier = modifier,
                        playerNameBackgroundModifier = playerNameBackgroundModifier,
                        bottomBackgroundModifier = bottomBackgroundModifier,
                        firstNameTextStyle = firstNameTextStyle,
                        lastNameTextStyle = lastNameTextStyle,
                        roleBottomTextStyle = roleBottomTextStyle,
                        staffDetail = supportStaffFilterData[it])
                }
            }
        }
    }
}