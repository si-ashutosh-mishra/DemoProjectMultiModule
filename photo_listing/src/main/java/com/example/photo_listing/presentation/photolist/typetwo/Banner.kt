package com.example.photo_listing.presentation.photolist.typetwo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.photo_listing.business.model.PhotoListingItem

@Composable
fun Banner(data : PhotoListingItem.Banner){
    Row {
        AsyncImage(model = data.bannerImage, contentDescription ="",
            contentScale = ContentScale.FillBounds)
    }
}