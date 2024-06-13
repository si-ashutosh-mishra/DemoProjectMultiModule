package com.example.feature_fixtures.presentation.fixture.typeone

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch

@Composable
fun LiveMatchCardTypeOne(
    data: IPLMatch?,
    isSponsorLogoRequired: Boolean = false,
    @DrawableRes sponsorLogo: Int,
    @DrawableRes recentLogo: Int = R.drawable.ic_live,
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
    highLightedScoreStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    highLightedOverStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    generalScoreStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    generalOverStyle: TextStyle = TextStyle(
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
                    .background(if (cardBackGroundColor != null) colorResource(id = cardBackGroundColor) else Color.Transparent)
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
                            text = data?.eventName?:"",
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
                            painterResource(recentLogo),
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
                        val teamA = data?.participants?.firstOrNull()
                        val teamB = data?.participants?.lastOrNull()
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painterResource(R.drawable.ic_menu_fixture),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.height(24.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = teamA?.name?:"",
                                    style = teamNameTextStyle
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                if (teamA?.value.isNullOrEmpty()) {
                                    Row {
                                        Text(text = "Yet to Bat")
                                    }
                                } else {
                                    if (teamA?.value?.contains("&") == true) {
                                        val teamAScoreArray = teamA?.value?.split("&")
                                        val score1 = teamAScoreArray?.get(0)?.trim()
                                        val score = score1?.trim()?.split(" ")
                                        val over = score?.lastOrNull()
                                        val list = mutableListOf<String>()
                                        Row {
                                            Text(text = score?.firstOrNull().toString(), style = if(teamA?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                            Text(text = over.toString(),style = if(teamA?.highlight == true)highLightedOverStyle else generalOverStyle)
                                        }
                                        teamAScoreArray?.reversed()
                                            ?.forEachIndexed { index, element ->
                                                if (teamAScoreArray.size <= 2) {
                                                    if (index == 0) {
                                                        val score = element?.trim()?.split(" ")
                                                        val over = score?.lastOrNull()
                                                        Row {
                                                            Text(text = score?.firstOrNull().toString(), style = if(teamA?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                                            Text(text = over.toString(),style = if(teamA?.highlight == true)highLightedOverStyle else generalOverStyle)
                                                        }
                                                    }
                                                } else {
                                                    if (list.size < 2) {
                                                        list.add(element)
                                                    }
                                                    list.reversed()
                                                        .forEachIndexed { index, element ->
                                                            if (index == 0) {
                                                                val score =
                                                                    element?.trim()?.split(" ")
                                                                val over = score?.lastOrNull()
                                                                Row {
                                                                    Text(text = score?.firstOrNull().toString(), style = if(teamA?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                                                    Text(text = over.toString(),style = if(teamA?.highlight == true)highLightedOverStyle else generalOverStyle)
                                                                }
                                                            }
                                                            if (index == 1) {
                                                                val score =
                                                                    element?.trim()?.split(" ")
                                                                val over = score?.lastOrNull()
                                                                Row {
                                                                    Text(text = score?.firstOrNull().toString(), style = if(teamA?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                                                    Text(text = over.toString(),style = if(teamA?.highlight == true)highLightedOverStyle else generalOverStyle)
                                                                }
                                                            }
                                                        }
                                                }
                                            }
                                    } else {
                                        val score = teamA?.value?.trim()?.split(" ")
                                        val over = score?.lastOrNull()
                                        Row {
                                            Text(text = score?.firstOrNull().toString(), style = if(teamA?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                            Text(text = over.toString(),style = if(teamA?.highlight == true)highLightedOverStyle else generalOverStyle)
                                        }
                                    }
                                }
                            }
                        }
                        Text(text = "VS")
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                if (teamB?.value.isNullOrEmpty()) {
                                    Row {
                                        Text(text = "Yet to Bat")
                                    }
                                } else {
                                    if (teamB?.value?.contains("&") == true) {
                                        val teamBScoreArray = teamB?.value?.split("&")
                                        val score1 = teamBScoreArray?.get(0)?.trim()
                                        val score = score1?.trim()?.split(" ")
                                        val over = score?.lastOrNull()
                                        val list = mutableListOf<String>()
                                        Row {
                                            Text(text = score?.firstOrNull().toString(), style = if(teamB?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                            Text(text = over.toString(),style = if(teamB?.highlight == true)highLightedOverStyle else generalOverStyle)
                                        }
                                        teamBScoreArray?.reversed()
                                            ?.forEachIndexed { index, element ->
                                                if (teamBScoreArray.size <= 2) {
                                                    if (index == 0) {
                                                        val score = element?.trim()?.split(" ")
                                                        val over = score?.lastOrNull()
                                                        Row {
                                                            Text(text = score?.firstOrNull().toString(), style = if(teamB?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                                            Text(text = over.toString(),style = if(teamB?.highlight == true)highLightedOverStyle else generalOverStyle)
                                                        }
                                                    }
                                                } else {
                                                    if (list.size < 2) {
                                                        list.add(element)
                                                    }
                                                    list.reversed()
                                                        .forEachIndexed { index, element ->
                                                            if (index == 0) {
                                                                val score =
                                                                    element?.trim()?.split(" ")
                                                                val over = score?.lastOrNull()
                                                                Row {
                                                                    Text(text = score?.firstOrNull().toString(), style = if(teamB?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                                                    Text(text = over.toString(),style = if(teamB?.highlight == true)highLightedOverStyle else generalOverStyle)
                                                                }
                                                            }
                                                            if (index == 1) {
                                                                val score =
                                                                    element?.trim()?.split(" ")
                                                                val over = score?.lastOrNull()
                                                                Row {
                                                                    Text(text = score?.firstOrNull().toString(), style = if(teamB?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                                                    Text(text = over.toString(),style = if(teamB?.highlight == true)highLightedOverStyle else generalOverStyle)
                                                                }
                                                            }
                                                        }
                                                }
                                            }
                                    } else {
                                        val score = teamB?.value?.trim()?.split(" ")
                                        val over = score?.lastOrNull()
                                        Row {
                                            Text(text = score?.firstOrNull().toString(), style = if(teamB?.highlight == true)highLightedScoreStyle else generalScoreStyle)
                                            Text(text = over.toString(),style = if(teamB?.highlight == true)highLightedOverStyle else generalOverStyle)
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painterResource(R.drawable.ic_menu_fixture),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.height(24.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = teamB?.name?:"",
                                    style = teamNameTextStyle
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = data?.venueName?:"",
                            style = matchStatusTextStyle
                        )
                    }
                }
            }
        }

    }
}