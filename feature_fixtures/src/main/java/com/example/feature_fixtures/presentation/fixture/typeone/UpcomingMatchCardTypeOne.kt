package com.example.feature_fixtures.presentation.fixture.typeone

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_fixtures.R

@Composable
fun UpcomingMatchCardTypeOne(
    isSponsorLogoRequired: Boolean = false,
    @DrawableRes sponsorLogo: Int,
    @DrawableRes upcomingLogo: Int = R.drawable.ic_upcoming,
    matchNumberTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    teamNameTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    matchStatusTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    timeStampTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes matchCardBackGroundImage: Int? = null,
    @ColorRes cardBackGroundColor: Int? = null,
    @ColorRes cardBorderColor: Int = R.color.black,
    ) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .padding(all = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(0.1.dp, colorResource(cardBorderColor))
        ) {
            Box(
                modifier = Modifier
                    .background(if(cardBackGroundColor!=null)colorResource(id = cardBackGroundColor) else Color.Transparent)
                    .fillMaxWidth()
            ) {
                if (matchCardBackGroundImage != null) {
                    Image(
                        painterResource(id = matchCardBackGroundImage),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds, // or some other scale
                        modifier = Modifier.matchParentSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Transparent)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Match 22",
                            style = matchNumberTextStyle
                        )

                        Spacer(Modifier.weight(1f))
                        if (isSponsorLogoRequired) {
                            Image(
                                painterResource(sponsorLogo),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                        Image(
                            painterResource(upcomingLogo),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(15.dp)
                                .background(Color.Black)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TSK",
                                style = teamNameTextStyle
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Image(
                                painterResource(R.drawable.ic_menu_fixture),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(24.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "06 july 2024",
                                style = timeStampTextStyle
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "06:00",
                                style = timeStampTextStyle
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painterResource(R.drawable.ic_menu_fixture),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "LAKR",
                                style = teamNameTextStyle
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Grand Prairie Cricket Stadium,Dallas",
                            style = matchStatusTextStyle
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CommonView() {

}