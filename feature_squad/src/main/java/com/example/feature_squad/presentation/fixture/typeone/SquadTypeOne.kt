package com.example.feature_squad.presentation.fixture.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.sp
import com.example.feature_squad.R
import com.example.feature_squad.presentation.fixture.typetwo.SquadTypeTwo

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SquadTypeOne()
}

@Composable
fun SquadTypeOne(
    @DrawableRes playerImage: Int = R.drawable.ic_player
){
    Box(
        modifier = Modifier
            .width(200.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        Image(painter = painterResource(playerImage),
            modifier = Modifier
                .width(20.dp)
                .height(15.dp)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Fit,
            contentDescription = "")
        Column {
            Image(
                painter = painterResource(playerImage),
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )

            Card(
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),

                ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(all = 8.dp)
                    ){
                        Row {
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
                    Box(modifier = Modifier
                        .fillMaxWidth()) {
                        Row {
                            Row (Modifier.weight(1f)){
                                StatText(
                                    "15", "ABC", valueStyle = TextStyle(
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    ), headingStyle = TextStyle(
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                )
                                Divider(
                                    color = Color.Red,
                                    modifier = Modifier
                                        .wrapContentHeight(Alignment.Top)
                                        .padding(5.dp)
                                        .height(25.dp)
                                        .width(1.dp)
                                )
                            }
                            Box (Modifier.weight(1f)) {
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
                            Divider(
                                color = Color.Red,
                                modifier = Modifier
                                    .wrapContentHeight(Alignment.Top)
                                    .padding(5.dp)
                                    .height(25.dp)
                                    .width(1.dp)
                            )
                            Box (Modifier.weight(1f)) {
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
        }
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .padding(all = 8.dp)
                .align(Alignment.CenterStart)
        ){
            Column {
                Text(text = "Shreyas")
                Text(text = "IYER",
                    style = TextStyle(color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ))
            }
        }
    }
}