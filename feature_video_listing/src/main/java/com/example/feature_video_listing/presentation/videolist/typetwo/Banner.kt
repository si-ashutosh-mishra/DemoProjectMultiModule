package com.example.feature_video_listing.presentation.videolist.typetwo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.feature_video_listing.business.domain.VideosListingItem


@Composable
fun Banner(data : VideosListingItem.Banner){
    Row {
        AsyncImage(model = data.bannerImage, contentDescription ="",
            contentScale = ContentScale.FillBounds)
    }
}