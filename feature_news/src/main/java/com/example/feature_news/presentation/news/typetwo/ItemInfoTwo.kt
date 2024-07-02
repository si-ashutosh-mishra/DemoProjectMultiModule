package com.example.feature_news.presentation.news.typetwo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_news.R
import com.example.feature_news.presentation.theme.share_gray
import com.example.feature_news.presentation.theme.white_50

@Composable
fun ItemInfoTwo(
    modifier: Modifier,
    publishedDate: String,
    likes: String,
    likesFontSize: TextUnit = 11.sp,
    publishedDateFontSize: TextUnit = 10.sp,
    @ColorRes publishedDateColor : Color = white_50,
    publishedDateFontFamily: FontFamily = FontFamily(Font(R.font.roboto_regular)),
    likesFontFamily: FontFamily = FontFamily(Font(R.font.roboto_regular)),
    @ColorRes likesTextColor : Color = white_50,
    @ColorRes pipeColor : Color = white_50,
    @ColorRes shareIcon : Color = share_gray,
    @DrawableRes unselectedLike: Int = R.drawable.ic_like_type1_gray,
){

    // Example of state to toggle image visibility
    val (isLiked, setIsLiked) = remember { mutableStateOf(false) }

    // Example of handling image click
    val onClick = { setIsLiked(!isLiked) }

    Spacer(modifier = modifier)
    Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom) {
        Image(
            painter = painterResource(id = R.drawable.ic_published_duration),
            contentDescription = "",
            modifier = Modifier
                .size(12.dp)
        )
        Text(
            text = publishedDate,
            overflow = TextOverflow.Ellipsis,
            fontSize = publishedDateFontSize,
            color = publishedDateColor,
            fontFamily = publishedDateFontFamily,
        )

        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "|",
            overflow = TextOverflow.Ellipsis,
            fontSize = 11.sp,
            color = pipeColor,
            fontFamily = likesFontFamily,
        )
        Spacer(modifier = Modifier.width(4.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_like),
            contentDescription = "",
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_share_type2),
                contentDescription = "",
                modifier = Modifier
                    .size(15.dp)
                    .clickable(onClick = onClick)
            )
            /*Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = null,
                tint = shareIcon,
                modifier = Modifier
                    .size(15.dp)
                    .clickable {
                    }
            )*/
        }
    }
}