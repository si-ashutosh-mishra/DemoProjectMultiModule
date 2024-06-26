package com.example.feature_news.presentation.news.typeone

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature_news.R
import com.example.feature_news.presentation.theme.light_gray
import com.example.feature_news.presentation.theme.purple
import com.example.feature_news.presentation.theme.white
import com.example.feature_news.presentation.theme.yellow
import com.knightclub.app.business.domain.model.listing.BannerItem

@Composable
fun CarouselTypeOneScreen(
    item: BannerItem,
    tagFontStyle: FontFamily = FontFamily(Font(R.font.rubik_medium)),
    tagFontSize: TextUnit = 10.sp,
    @ColorRes tagColor: Color =  light_gray,
    @ColorRes tagTextColor: Color =  yellow,
    carouselFontFamily: FontFamily = FontFamily(Font(R.font.urbanist_bold)),
    carouselFontSize: TextUnit = 20.sp
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { },
        contentAlignment = Alignment.BottomStart
    ) {

        AsyncImage(model = item.bannerImageUrl
            ?: "https://www.knightclub.in/static-assets/waf-images/83/a8/3c/4-3/MSJZcruTTP.jpg?v=1.30",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xCC000000), Color.Black),
                        startY = size.height / 2,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                color = tagTextColor,
                fontSize = tagFontSize,
                text = item.tag ?: "",
                fontFamily = tagFontStyle,
                modifier = Modifier
                    .background(
                        color = tagColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = item.title ?: "",
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontFamily = carouselFontFamily,
                fontSize = carouselFontSize
                )
            Spacer(modifier = Modifier.height(16.dp))
            ItemDateTimeLikeShare(
                publishedDate = item.publishedDate ?: "",
                likes = "2.7K",
                publishedDateColor = white,
                likesTextColor = white,
                pipeColor = white,
                shareIcon = white,
                unselectedLike = R.drawable.ic_like_type1_white
            )
        }
    }
}