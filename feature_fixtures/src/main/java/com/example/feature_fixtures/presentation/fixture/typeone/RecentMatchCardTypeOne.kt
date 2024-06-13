package com.example.feature_fixtures.presentation.fixture.typeone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_fixtures.R

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun RecentMatchCardTypeOne(

) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(0.5.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White)
                .fillMaxWidth()
                .padding(all = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            )
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Match 22",
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(Modifier.weight(1f))
                    Image(
                        painterResource(R.drawable.ic_menu_fixture),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painterResource(R.drawable.ic_menu_fixture),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(24.dp)
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
                            style = TextStyle(
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
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
                            style = TextStyle(
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "06:00",
                            style = TextStyle(
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
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
                            text = "TSK",
                            style = TextStyle(
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
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
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

        }

    }
}