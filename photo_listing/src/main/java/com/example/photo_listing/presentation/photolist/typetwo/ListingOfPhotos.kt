package com.example.photo_listing.presentation.photolist.typetwo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.photo_listing.business.listing.AssetItem


@Composable
fun ListingOfPhotos(assetItem: AssetItem) {
    Card (elevation = 5.dp){
        Column(verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = assetItem.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
            )

            Text(
                text = assetItem.assetTitle.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Row {
                AsyncImage(model = "", contentDescription = assetItem.totalReacts)

                AsyncImage(model = "", contentDescription = "")
            }
        }
    }
}