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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.feature_squad.R
import com.example.feature_squad.business.domain.model.squad.PlayerItem

@Preview
@Composable
fun ScreenPreview() {
    SquadTypeOne()
}

@Composable
fun SquadTypeOne(
    playerImageModifier: Modifier = Modifier
        .height(200.dp)
        .width(250.dp)
        .background(Color.Transparent)
        .fillMaxWidth(),
    @DrawableRes homeSquadBackground: Int = R.drawable.lakr_squad_bg,
    backgroundPlayerName : Color = Color.Yellow,
    bottomBackground : Color = Color.Magenta,
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
    skillTextStyle : TextStyle = TextStyle(color = Color.Black,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold
    ),
    playerDetail: PlayerItem? = null
){
    Box(
        modifier = playerImageModifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(homeSquadBackground),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )
        Row (modifier = Modifier.align(Alignment.TopEnd)){
            if (playerDetail != null) {
                if(playerDetail.overseasPlayer) {
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
                if(playerDetail.isCaptain){
                    Image(painter = painterResource(R.drawable.ic_captain_home),
                        modifier = Modifier
                            .width(20.dp)
                            .height(15.dp),
                        contentScale = ContentScale.Fit,
                        contentDescription = "")
                }
            }
        }
        Column {
            AsyncImage(
                model = playerDetail?.playerImageUrl,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )
        }
        Card (shape = RoundedCornerShape(topEnd = 5.dp),
            modifier = Modifier.align(Alignment.BottomStart),
        ) {
            Card(
                modifier = Modifier
                    .background(bottomBackground)
                    .padding(bottom = 45.dp, top = 5.dp, start = 3.dp)
            ) {
                Row(modifier = Modifier.background(bottomBackground)) {
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

        Card(
            modifier = Modifier
                .background(bottomBackground)
                .padding(all = 4.dp)
                .align(Alignment.BottomStart),
            shape = RoundedCornerShape(0.dp),
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Magenta),) {
                    Row {
                        Row (Modifier.weight(1f), horizontalArrangement = Arrangement.Center){
                            StatText(
                                playerDetail?.overAllStats?.batting?.matchesPlayed.toString(), "Matches", valueStyle = playerRoleValueTextStyle, headingStyle =  playerRoleHeadingTextStyle
                            )
                        }
                        Divider(
                            color = Color.White,
                            modifier = Modifier
                                .wrapContentHeight(Alignment.Top)
                                .padding(5.dp)
                                .height(25.dp)
                                .width(1.dp)
                        )
                        Row (Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                            StatText(
                                playerDetail?.overAllStats?.batting?.runs.toString(), "Runs", valueStyle = playerRoleValueTextStyle, headingStyle =  playerRoleHeadingTextStyle
                            )
                        }
                        Divider(
                            color = Color.White,
                            modifier = Modifier
                                .wrapContentHeight(Alignment.Top)
                                .padding(5.dp)
                                .height(25.dp)
                                .width(1.dp)
                        )
                        Row (Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                            StatText(
                                playerDetail?.overAllStats?.bowling?.wickets.toString(), "Wickets", valueStyle = playerRoleValueTextStyle, headingStyle =  playerRoleHeadingTextStyle
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .background(backgroundPlayerName)
                .padding(all = 8.dp)
                .align(Alignment.CenterStart)
        ){
            Column {
                Text(text = playerDetail?.firstName.toString(),
                    style = firstNameTextStyle)
                Text(text = playerDetail?.lastName.toString(),
                    style = lastNameTextStyle)
            }
        }
    }
}