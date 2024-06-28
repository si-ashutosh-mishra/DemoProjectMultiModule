package com.example.feature_squad.presentation.squad.typetwo

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_squad.R
import com.example.feature_squad.presentation.squad.viewmodel.SquadViewModel

//@Preview
//@Composable
//fun Preview() {
//    SquadHorizontalScroll()
//}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SquadHorizontalScroll (
    @DrawableRes homeSquadBackground: Int = R.drawable.lakr_squad_bg,
    firstNameTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    ),
    lastNameTextStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerRoleTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Yellow,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    squadTitleTxtStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 20.sp
    )
) {

    val viewModel: SquadViewModel = hiltViewModel()
    val squadList by viewModel.player.observeAsState(initial = emptyList())
    val pagerState = rememberPagerState { squadList.size }

    Box (modifier = Modifier.wrapContentSize()) {

        Image(
            painter = painterResource(homeSquadBackground),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )

        Column  {
            HomeSquadHeadline(
                modifier = Modifier.padding(
                    top = 50.dp
                ), squadTitleTxtStyle = squadTitleTxtStyle
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            HorizontalPager(
                modifier = Modifier.padding(vertical = 8.dp),
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 55.dp),
                pageSpacing = 15.dp
            ) { page ->
                val data = squadList[page]
                Log.d("Player $page", data.toString())
                Box(modifier = Modifier.graphicsLayer {
                    alpha = if (page == pagerState.currentPage) 1f else 0.5f
                }) {
                    SquadTypeTwo(
                        playerImageModifier = Modifier.height(250.dp),
                        firstNameTextStyle = firstNameTextStyle,
                        lastNameTextStyle = lastNameTextStyle,
                        playerRoleTextStyle = playerRoleTextStyle,
                        playerDetail = data
                    )
                }
            }
        }
    }
}

@Composable
fun HomeSquadHeadline(
    modifier: Modifier = Modifier,
    squadTitle: String = "Squad",
    squadTitleTxtStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 20.sp
    ),
    moreButtonModifier: Modifier = Modifier
        .padding(end = 8.dp)
        .clip(
            shape = RoundedCornerShape(2.dp)
        )
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(4.dp)
        )
        .border(
            BorderStroke(0.5.dp, SolidColor(Color.White)),
            shape = RoundedCornerShape(4.dp)
        )
        .padding(horizontal = 12.dp, vertical = 6.dp),
    moreButtonTextStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 10.sp
    )

){

    Row (
        modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            text = squadTitle.uppercase(),
            style = squadTitleTxtStyle,
        )

        Text(
            modifier = moreButtonModifier
                .align(Alignment.CenterVertically)
            ,
            textAlign = TextAlign.End,
            text = "More",
            style = moreButtonTextStyle
        )
    }
}