package com.example.feature_squad.presentation.squad.typeone

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_squad.R
import com.example.feature_squad.presentation.squad.viewmodel.SquadViewModel

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SquadTypeOneHorizontalScroll (
    @DrawableRes homeSquadBackground: Int = R.drawable.lakr_squad_bg,
    backgroundPlayerName : Color = Color.Yellow,
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
    ),
    playerRoleValueTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Yellow,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerRoleHeadingTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Yellow,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    squadTitleTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    squadTitle: String = "Players",
    moreButtonModifier: Modifier = Modifier
        .background(
            color = Color.Blue,
            shape = RoundedCornerShape(20.dp)
        )
        .height(22.dp)
        .padding(horizontal = 12.dp, vertical = 6.dp),
    moreButtonTextStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 10.sp
    ),
    @DrawableRes moreButtonArrow: Int = R.drawable.arrow,
    moreButtonArrowModifier : Modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 6.dp)
        .background(
            color = Color.Yellow,
            shape = RoundedCornerShape(20.dp)
        )
        .height(22.dp)
) {

    //val viewModel: SquadViewModel = hiltViewModel()
    //val squadList by viewModel.player.observeAsState(initial = emptyList())


    Box (modifier = Modifier
        .wrapContentSize()
        .background(Color.White)) {

        Column  {
            HomeSquadHeadline(
                modifier = Modifier.padding(
                    start = 20.dp, top = 50.dp
                ), squadTitleTextStyle = squadTitleTextStyle
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyRow(
                modifier = Modifier.padding(vertical = 8.dp),
                //state = pagerState,
                contentPadding = PaddingValues(horizontal = 55.dp),
                //pageSpacing = 15.dp
            ) {
                items(4) { page ->
                    //val data = squadList[page]
                    //Log.d("Player $page", data.toString())
                        SquadTypeOne(
                            playerImageModifier = Modifier.height(250.dp),
                            firstNameTextStyle = firstNameTextStyle,
                            lastNameTextStyle = lastNameTextStyle,
                            playerRoleValueTextStyle = playerRoleValueTextStyle,
                            playerRoleHeadingTextStyle = playerRoleHeadingTextStyle,
                            playerDetail = null
                        )

                }
            }
        }
    }
}

@Composable
fun HomeSquadHeadline(
    modifier: Modifier = Modifier,
    squadTitle: String = "Players",
    squadTitleTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    moreButtonModifier: Modifier = Modifier
        .background(
            color = Color.Blue,
            shape = RoundedCornerShape(20.dp)
        )
        .padding(horizontal = 12.dp, vertical = 6.dp),
    moreButtonTextStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 12.sp
    ),
    @DrawableRes moreButtonArrow: Int = R.drawable.arrow,
    moreButtonArrowModifier : Modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 6.dp)
        .background(
            color = Color.Yellow,
            shape = RoundedCornerShape(20.dp)
        )
    ){

    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            text = squadTitle.uppercase(),
            style = squadTitleTextStyle,
        )
        Row (modifier = moreButtonArrowModifier, verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = moreButtonModifier,
                textAlign = TextAlign.End,
                text = "VIEW ALL",
                style = moreButtonTextStyle
            )
            Image(
                modifier = Modifier.padding(horizontal = 2.dp).height(10.dp).width(10.dp),
                painter = painterResource(moreButtonArrow),
                contentDescription = "",
                alignment = Alignment.Center
            )
        }
    }
}