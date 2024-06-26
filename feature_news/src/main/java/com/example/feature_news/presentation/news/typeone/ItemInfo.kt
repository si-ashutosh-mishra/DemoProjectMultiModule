package com.example.feature_news.presentation.news.typeone

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_news.R
import com.example.feature_news.presentation.theme.share_gray
import com.example.feature_news.presentation.theme.textcolor_gray
import com.knightclub.app.business.domain.model.listing.BannerItem

@Composable
fun ItemDateTimeLikeShare(
    publishedDate: String,
    likes: String,
    likesFontSize: TextUnit = 10.sp,
    publishedDateFontSize: TextUnit = 10.sp,
    @ColorRes publishedDateColor : Color = textcolor_gray,
    publishedDateFontFamily: FontFamily = FontFamily(Font(R.font.rubik_regular)),
    likesFontFamily: FontFamily = FontFamily(Font(R.font.rubik_regular)),
    @ColorRes likesTextColor : Color = textcolor_gray,
    @ColorRes pipeColor : Color = textcolor_gray,
    @ColorRes shareIcon : Color = share_gray,
    @DrawableRes unselectedLike: Int = R.drawable.ic_like_type1_gray,
    ){

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = publishedDate,
            overflow = TextOverflow.Ellipsis,
            fontSize = publishedDateFontSize,
            color = publishedDateColor,
            fontFamily = publishedDateFontFamily,
        )

        // Example of state to toggle image visibility
        val (isLiked, setIsLiked) = remember { mutableStateOf(false) }

        // Example of handling image click
        val onClick = { setIsLiked(!isLiked) }
        Row() {
            Image(
                painter = painterResource(id = if (isLiked) R.drawable.ic_like_active_type1 else unselectedLike),
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(18.dp)
                    .clickable(onClick = onClick)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = likes,
                overflow = TextOverflow.Ellipsis,
                fontSize = likesFontSize,
                color = likesTextColor,
                fontFamily = likesFontFamily,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "|",
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                color = pipeColor,
                fontFamily = likesFontFamily,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = null,
                tint = shareIcon,
                modifier = Modifier
                    .size(15.dp)
                    .clickable {
                    }
            )
        }
    }
}