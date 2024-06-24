package com.example.feature_squad.presentation.squad.typetwo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.presentation.squad.viewmodel.SquadViewModel

//@Preview
//@Composable
//fun Preview() {
//    SquadHorizontalScroll()
//}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SquadHorizontalScroll (
//    list: List<PlayerItem> = listOf(),
) {
    val viewModel: SquadViewModel = hiltViewModel()
    val squadList by viewModel.player.observeAsState(initial = emptyList())

    Column  {
        val pagerState = rememberPagerState { squadList.size }

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                text = "Squad".uppercase(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp
                )
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
                    .clip(
                        shape = RoundedCornerShape(2.dp)
                    )
                    .background(
                        color = Color(0xFFF2C029),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        BorderStroke(1.dp, SolidColor(Color.White)),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    ,
                textAlign = TextAlign.End,
                text = "more".uppercase(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp
                )
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 15.dp),
            pageSpacing = 0.dp
        ) {
            SquadTypeTwo(
                playerImageModifier = Modifier.height(150.dp),
                firstNameTextStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                lastNameTextStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                playerRoleTextStyle = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Yellow,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
            )
        }
    }
}