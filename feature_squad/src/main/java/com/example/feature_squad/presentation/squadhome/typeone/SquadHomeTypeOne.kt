package com.example.feature_squad.presentation.squadhome.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_squad.R
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.presentation.squadhome.viewmodel.SquadViewModel

@Preview
@Composable
fun SquadHomeTypeOne (
    @DrawableRes homeSquadBackground: Int = R.drawable.lakr_squad_bg,
    backgroundPlayerName : Color = Color.Yellow,
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
        fontSize = 12.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    squadTitleTextStyle: TextStyle = TextStyle(
        color = Color(0xFF252525),
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    squadTitle: String = "Players",
    moreButtonModifier: Modifier = Modifier
        .background(
            color = Color(0xFF3A225D),
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
            color = Color(0xFFF2C029),
            shape = RoundedCornerShape(20.dp)
        )
        .height(22.dp),
    players: List<PlayerItem> = emptyList(),
    onPlayerItemClick: (PlayerItem?) -> Unit = {},
    onMoreClick: () -> Unit = {},
) {
    Box (modifier = Modifier
        .wrapContentSize()
        .background(Color.White)) {

        Column  {
            HomeSquadHeadline(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                squadTitle = squadTitle,
                squadTitleTextStyle = squadTitleTextStyle,
                moreButtonModifier = moreButtonModifier,
                moreButtonTextStyle = moreButtonTextStyle,
                moreButtonArrow = moreButtonArrow,
                moreButtonArrowModifier = moreButtonArrowModifier,
                onMoreClick = onMoreClick
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyRow(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 5.dp),
            ) {
                items(players.size) {
                    val data = players[it]
                        SquadTypeOneItem(
                            firstNameTextStyle = firstNameTextStyle,
                            lastNameTextStyle = lastNameTextStyle,
                            playerRoleValueTextStyle = playerRoleValueTextStyle,
                            playerRoleHeadingTextStyle = playerRoleHeadingTextStyle,
                            playerDetail = data,
                            onPlayerItemClick = onPlayerItemClick
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
            color = Color(0xFF3A225D),
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
            color = Color(0xFFF2C029),
            shape = RoundedCornerShape(20.dp)
        )
        .height(22.dp),
    onMoreClick: () -> Unit = {},
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
        Row (
            modifier = moreButtonArrowModifier
                .clickable(enabled = true) { onMoreClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
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