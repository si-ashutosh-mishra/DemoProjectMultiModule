package com.example.photo_listing.presentation.photolist.typetwo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListingOfPhotos(){
    Card (elevation = 5.dp,
        modifier = Modifier.fillMaxSize()){
        Column(verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
            )

            Text(
                text = "The Final: TKR VS GAW",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Row {
                AsyncImage(model = "", contentDescription = "9m")

                AsyncImage(model = "", contentDescription = "")
            }
        }
    }
}