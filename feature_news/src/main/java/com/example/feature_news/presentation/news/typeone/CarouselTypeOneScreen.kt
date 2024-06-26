package com.example.feature_news.presentation.news.typeone

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature_news.R
import com.knightclub.app.business.domain.model.listing.BannerItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselTypeOneScreen(
    item: BannerItem,
    tagFontStyle: FontFamily = FontFamily(Font(R.font.rubik_medium)),
    pageCount: Int,
    pagerState: PagerState
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { },
        contentAlignment = Alignment.BottomStart
    ) {

        /*Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1 / 1.3f),
            contentAlignment = Alignment.BottomStart
        ){*/
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
                color = Color.Yellow,//colorResource(id = tagTextColor),
                fontSize = 10.sp,//tagFontSize,
                text = item.tag ?: "",
                fontFamily = tagFontStyle,
                modifier = Modifier
                    .background(
                        color = Color.Black,//colorResource(tagColor),
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
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                fontSize = 20.sp
                )
            Spacer(modifier = Modifier.height(16.dp))
            ItemDateTimeLikeShare(
                publishedDate = item.publishedDate ?: "",
                likes = "2.7K",
                publishedDateColor = R.color.white,
                likesTextColor = R.color.white,
                pipeColor = R.color.white,
                shareIcon = R.color.white,
                unselectedLike = R.drawable.ic_like_type1_white
            )
            /*Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                //Text(text = "kk", color = Color.Red)

                androidx.compose.material.Text(
                    text = item.publishedDate ?: "",
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 10.sp,//publishedDateFontSize,
                    color = Color.Red,//colorResource(id = publishedDateColor),
                    //fontFamily = publishedDateFontFamily,
                )

                // Example of state to toggle image visibility
                val (isLiked, setIsLiked) = remember { mutableStateOf(false) }

                // Example of handling image click
                val onClick = { setIsLiked(!isLiked) }
                Row() {
                    Image(
                        painter = painterResource(id = if (isLiked) R.drawable.ic_like_active_type1 else R.drawable.ic_like_type1),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable(onClick = onClick)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "2.4K",
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp,
                        color = Color(0xFF252525),
                        fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "|",
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp,
                        color = Color(0xFF252525),
                        fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(15.dp)
                            .clickable {
                            }
                    )
                    *//*Image(
                        painter = painterResource(id = R.drawable.ic_share_type1),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(15.dp),
                            //.clickable(onClick = onClick)
                    )*//*
                }
            }*/
            //Spacer(modifier = Modifier.height(10.dp))
        }

        /*Column {
            Text(text = item.title ?: "")
        }*/
        //}


    }

    /*AsyncImage(
        model = item.bannerImageUrl ?:"https://www.imgacademy.com/sites/default/files/styles/scale_1700w/public/2022-07/img-homepage-meta_0.jpg?itok=LMirU0Ik",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(336.dp),
        //placeholder = painterResource(id = androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
    )*/
}