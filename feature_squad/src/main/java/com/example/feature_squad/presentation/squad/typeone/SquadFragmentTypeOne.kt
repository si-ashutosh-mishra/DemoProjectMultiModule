package com.example.feature_squad.presentation.squad.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.feature_squad.business.domain.model.squad.StaffItem
import com.example.feature_squad.presentation.squadhome.typeone.StatText

@Preview
@Composable
fun SquadFragmentScreenPreview() {
    SquadFragmentTypeOne()
}

@Composable
fun SquadFragmentTypeOne(
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
    playerDetail: PlayerItem? = null
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(homeSquadBackground),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )

        Column(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(end = 10.dp, top = 10.dp)) {
            if (playerDetail != null) {
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
            }
        }

        ConstraintLayout {

            val (playerImageId, playerStatsCard, skillDetail, playerDetails) = createRefs()

            AsyncImage(
                model = playerDetail?.playerImageUrl,
                placeholder = painterResource(R.drawable.ic_player),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .constrainAs(playerImageId) {
                        bottom.linkTo(playerStatsCard.top)
                        height = Dimension.fillToConstraints
                    },
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )

            Column(
                modifier = playerNameBackgroundModifier
                    .constrainAs(playerDetails) {
                        bottom.linkTo(skillDetail.top)
                    }
                    .fillMaxWidth(0.95f)
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = playerDetail?.firstName.toString(),
                    style = firstNameTextStyle
                )
                Text(
                    text = playerDetail?.lastName.toString(),
                    style = lastNameTextStyle
                )
            }

            Card(
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
                            model = if (playerDetail?.skillId.equals("1")) {
                                R.drawable.ic_skill_bat
                            } else if (playerDetail?.skillId.equals("2")) {
                                R.drawable.ic_skill_bowl
                            } else if (playerDetail?.skillId.equals("4")) {
                                R.drawable.ic_skill_wicket_keeper
                            } else {
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

            Row(
                modifier = bottomBackgroundModifier
                    .constrainAs(playerStatsCard) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 6.dp)
                ,horizontalArrangement = Arrangement.Center
                ,verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(), horizontalArrangement = Arrangement.Center
                ) {
                    StatText(
                        playerDetail?.playerStats?.matches.toString(),
                        "Matches",
                        valueStyle = playerRoleValueTextStyle,
                        headingStyle = playerRoleHeadingTextStyle
                    )
                }

                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(25.dp)
                        .width(1.dp)
                )

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(), horizontalArrangement = Arrangement.Center
                ) {
                    if(playerDetail?.skillId.equals("2")){
                        StatText(
                            playerDetail?.playerStats?.wickets.toString(),
                            "Wickets",
                            valueStyle = playerRoleValueTextStyle,
                            headingStyle = playerRoleHeadingTextStyle
                        )
                    }else {
                        StatText(
                            playerDetail?.playerStats?.runs.toString(),
                            "Runs",
                            valueStyle = playerRoleValueTextStyle,
                            headingStyle = playerRoleHeadingTextStyle
                        )
                    }
                }
                if(playerDetail?.skillId.equals("3")){
                    Divider(
                        color = Color.White,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(25.dp)
                            .width(1.dp)
                    )

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
                    ) {
                        StatText(
                            playerDetail?.playerStats?.wickets.toString(),
                            "Wickets",
                            valueStyle = playerRoleValueTextStyle,
                            headingStyle = playerRoleHeadingTextStyle
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StaffFragmentTypeOne(
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
    roleBottomTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.White,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    ),
    staffDetail: StaffItem? = null
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(homeSquadBackground),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )

        ConstraintLayout {

            val (playerImageId, playerStatsCard, skillDetail, playerDetails) = createRefs()

            AsyncImage(
                model = staffDetail?.staffImageUrl,
                placeholder = painterResource(R.drawable.ic_player),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .constrainAs(playerImageId) {
                        bottom.linkTo(playerStatsCard.top)
                        height = Dimension.fillToConstraints
                    },
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )

            Column(
                modifier = playerNameBackgroundModifier
                    .constrainAs(playerDetails) {
                        bottom.linkTo(skillDetail.top)
                    }
                    .padding(all = 8.dp)
                    .fillMaxWidth(0.95f)
            ) {
                Text(
                    text = staffDetail?.firstName.toString(),
                    style = firstNameTextStyle
                )
                Text(
                    text = staffDetail?.lastName.toString(),
                    style = lastNameTextStyle
                )
            }

            Box(
                modifier = bottomBackgroundModifier
                    .constrainAs(playerStatsCard) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = staffDetail?.roleName.toString(),
                    style = roleBottomTextStyle
                )
            }
        }
    }
}