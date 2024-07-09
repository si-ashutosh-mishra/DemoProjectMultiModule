package com.example.feature_squad.presentation.squadhome.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.feature_squad.R
import com.example.feature_squad.business.domain.model.squad.PlayerItem

@Preview
@Composable
fun ScreenPreview() {
    SquadTypeOneItem()
}

@Composable
fun SquadTypeOneItem(
    modifier: Modifier = Modifier
        .width(200.dp)
        .background(Color.Transparent)
        .fillMaxWidth(),
    @DrawableRes homeSquadBackground: Int = R.drawable.bg_player_home,
    playerNameBackgroundModifier : Modifier = Modifier
        .padding(bottom = 10.dp)
        .background(Color(0xFFF2C029)),
    bottomBackgroundModifier: Modifier = Modifier.background(Color(0xFF3A225D)),
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
        fontSize = 14.sp,
        color = Color.Yellow,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerRoleHeadingTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Yellow,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    ),
    skillTextStyle : TextStyle = TextStyle(color = Color.White,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold
    ),
    playerDetail: PlayerItem? = null,
    onPlayerItemClick: (PlayerItem?) -> Unit = {},
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Transparent)
            .clickable(enabled = true) {
                onPlayerItemClick(playerDetail)
            }
    ) {
        Image(
            painter = painterResource(homeSquadBackground),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )

        Column (modifier = Modifier.align(Alignment.TopEnd).padding(end = 15.dp, top = 10.dp)){
            if (playerDetail != null) {
                if (playerDetail.isCaptain) {
                    Image(
                        painter = painterResource(R.drawable.ic_captain_home),
                        modifier = Modifier
                            .width(20.dp)
                            .height(15.dp),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                }
                if (playerDetail.overseasPlayer) {
                    Image(
                        painter = painterResource(R.drawable.ic_home_overseas),
                        modifier = Modifier
                            .width(20.dp)
                            .height(15.dp),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                }
            }
        }

        ConstraintLayout {

            val (playerImageId, playerStatsCard, skillDetail, playerDetails) = createRefs()

            AsyncImage(
                model = playerDetail?.playerImageUrl,
                placeholder = painterResource(R.drawable.ic_player),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .constrainAs(playerImageId) {
                        bottom.linkTo(playerStatsCard.top)
                        height = Dimension.fillToConstraints
                    },
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )

            Column (
                modifier = playerNameBackgroundModifier
                    .constrainAs(playerDetails) {
                        bottom.linkTo(skillDetail.top)
                }
                    .padding(all = 8.dp)
//                    .align(Alignment.CenterStart)
            ) {
                Text(text = playerDetail?.firstName.toString(),
                    style = firstNameTextStyle)
                Text(text = playerDetail?.lastName.toString(),
                    style = lastNameTextStyle)
            }

            Card (
                shape = RoundedCornerShape(topEnd = 5.dp),
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(skillDetail) {
                        bottom.linkTo(playerStatsCard.top)
                        start.linkTo(parent.start)
                    },
            ) {
                Card(
                    bottomBackgroundModifier
                        .padding(vertical = 3.dp, horizontal = 5.dp)
                ) {
                    Row(modifier = bottomBackgroundModifier) {
                        AsyncImage(
                            model = if(playerDetail?.skillId.equals("1")){
                                R.drawable.ic_skill_bat
                            } else if(playerDetail?.skillId.equals("2")){
                                R.drawable.ic_skill_bowl
                            } else if(playerDetail?.skillId.equals("4")){
                                R.drawable.ic_skill_wicket_keeper
                            }else{
                                R.drawable.ic_skill_all_rounder
                            },
                            modifier = Modifier
                                .width(20.dp)
                                .height(15.dp),
                            contentScale = ContentScale.Fit,
                            contentDescription = ""
                        )
                        Text(
                            text = playerDetail?.skill.toString(),
                            modifier = Modifier.padding(end = 5.dp),
                            style = skillTextStyle
                        )
                    }
                }
            }

            Row (
                modifier = bottomBackgroundModifier
                    .constrainAs(playerStatsCard) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 4.dp)
            ){
                Row (
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(), horizontalArrangement = Arrangement.Center){
                    StatText(
                        playerDetail?.overAllStats?.batting?.matchesPlayed.toString(), "Matches", valueStyle = playerRoleValueTextStyle, headingStyle =  playerRoleHeadingTextStyle
                    )
                }

                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(25.dp)
                        .width(1.dp)
                )

                Row (
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(), horizontalArrangement = Arrangement.Center) {
                    StatText(
                        playerDetail?.overAllStats?.batting?.runs.toString(), "Runs", valueStyle = playerRoleValueTextStyle, headingStyle =  playerRoleHeadingTextStyle
                    )
                }

                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(25.dp)
                        .width(1.dp)
                )

                Row (
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(), horizontalArrangement = Arrangement.Center) {
                    StatText(
                        playerDetail?.overAllStats?.bowling?.wickets.toString(), "Wickets", valueStyle = playerRoleValueTextStyle, headingStyle =  playerRoleHeadingTextStyle
                    )
                }
            }
        }
    }
}


@Composable
fun StatText(
//    modifier: Modifier = Modifier.weight(1f),
    value : String,
    heading : String,
    valueStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    headingStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    )
){
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = value,
            style = valueStyle)
        Text(text = heading.uppercase(),
            style = headingStyle)
    }
}