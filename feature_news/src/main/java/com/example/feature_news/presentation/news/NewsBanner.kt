package com.example.feature_news.presentation.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.feature_news.business.domain.model.news.NewsListingItem

@Composable
fun NewsBanner(banner: NewsListingItem.Banner) {
    Box(modifier = Modifier.fillMaxWidth().height(120.dp)){
        AsyncImage(
            model = banner.bannerImage,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(10.dp)),
        )
    }
}