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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_squad.R

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SquadTypeOne()
}

@Composable
fun SquadTypeOne(

    @DrawableRes playerImage: Int = R.drawable.ic_player,
    backgroundPlayerName : Color = Color.Yellow,
    bottomBackground : Color = Color.Magenta,
    playerFirstName : String = "Shreyas",
    playerFirstNameStyle: TextStyle  = TextStyle(color = Color.Black,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium
    ),
    playerLastName : String = "IYER",
    playerLastNameStyle: TextStyle  = TextStyle(color = Color.Black,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    ),
    isCaptain : Boolean = false,
    isOverseas : Boolean = false,
    
){
    Box(
        modifier = Modifier
            .width(250.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        Row (modifier = Modifier.align(Alignment.TopEnd)){
            if(isOverseas) {
                Image(
                    painter = painterResource(R.drawable.ic_overseas),
                    modifier = Modifier
                        .width(20.dp)
                        .height(15.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = ""
                )
            }
            if(isCaptain){
            Image(painter = painterResource(R.drawable.ic_captain),
                modifier = Modifier
                    .width(20.dp)
                    .height(15.dp),
                contentScale = ContentScale.Fit,
                contentDescription = "")
                }
        }
        Column {
            Image(
                painter = painterResource(playerImage),
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )
        }
        Card(
            modifier = Modifier
                .background(bottomBackground)
                .padding(bottom = 45.dp, top = 8.dp, start = 8.dp, end = 8.dp)
                .align(Alignment.BottomStart),
        ){
            Row(modifier = Modifier.background(bottomBackground)){
                Image(painter = painterResource(playerImage),
                    modifier = Modifier
                        .width(20.dp)
                        .height(15.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = "")
                Text(text = "Batter",
                    style = TextStyle(color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ))
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
                                "15", "Matches", valueStyle = TextStyle(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                ), headingStyle = TextStyle(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
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
                                "351", "Runs", valueStyle = TextStyle(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                ), headingStyle = TextStyle(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
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
                                "Test", "ABC", valueStyle = TextStyle(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                ), headingStyle = TextStyle(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
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
                Text(text = playerFirstName,
                    style = playerFirstNameStyle)
                Text(text = playerLastName,
                    style = playerLastNameStyle)
            }
        }
    }
}