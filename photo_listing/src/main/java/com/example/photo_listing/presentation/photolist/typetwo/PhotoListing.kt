package com.example.photo_listing.presentation.photolist.typetwo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photo_listing.business.model.PhotoListingItem

@Composable
fun PhotoListing(
    data : PhotoListingItem.PhotosArticle
){
    Box (modifier = Modifier
        .background(Color.Blue)
        .fillMaxSize()
        .padding(5.dp, 5.dp, 5.dp, 5.dp)){

        Column {
            Row (horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier.fillMaxWidth()){
                Text(text = data.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )

                Button(onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(text = "More",
                        color = Color.White)

                }
            }

            LazyRow (modifier = Modifier.fillMaxSize()){
                items(data.items){
                    ListingOfPhotos(it)
                }
            }
        }
    }
}