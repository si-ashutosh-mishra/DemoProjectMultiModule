package com.example.feature_news.presentation.news.typeone

import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature_news.R
import com.example.feature_news.business.domain.model.listing.AssetItem

@Composable
fun ItemCategoryArticle(
    title: String,
    titleFontSize: TextUnit,
    titleFontStyle: FontFamily,
    assetList: List<AssetItem>,
    tagFontSize: TextUnit = 10.sp,
    @ColorRes tagColor: Int =  R.color.light_gray,
    @ColorRes tagTextColor: Int =  R.color.purple,
    tagRadious: Dp = 20.dp,
    tagFontStyle: FontFamily = FontFamily(Font(R.font.rubik_medium)),
    newsTitleFontSize: TextUnit = 14.sp,
    newsTitleFontFamily: FontFamily = FontFamily(Font(R.font.rubik_regular)),
    @ColorRes newsTitleColor : Int = R.color.textcolor_gray,
    publishedDateFontSize: TextUnit = 10.sp,
    publishedDateFontFamily: FontFamily = FontFamily(Font(R.font.rubik_regular)),
    @ColorRes publishedDateColor : Int = R.color.textcolor_gray,
) {
    val mContext = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title.uppercase(),
            fontSize = titleFontSize,
            fontFamily = titleFontStyle
        )

        Text(text = "View All".uppercase(), fontSize = 13.sp)//rubik font
        /*Image(
            painter = painterResource(id = R.drawable.kkr_bg_more_button),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )*/
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        itemsIndexed(assetList){ _, listItem ->
            listItem.let { item ->
                Column(modifier = Modifier.width(180.dp)) {
                    AsyncImage(
                        model = item.imageUrl ?:"https://www.imgacademy.com/sites/default/files/styles/scale_1700w/public/2022-07/img-homepage-meta_0.jpg?itok=LMirU0Ik",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(165.dp)
                            .height(111.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        //placeholder = painterResource(id = androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        color = colorResource(id = tagTextColor),
                        fontSize = tagFontSize,
                        text = item.tag ?: "",
                        fontFamily = tagFontStyle,
                        modifier = Modifier
                            .background(
                                color = colorResource(tagColor),
                                shape = RoundedCornerShape(tagRadious)
                            )
                            .padding(horizontal = 14.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = item.assetTitle ?: "",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = newsTitleFontSize,
                        fontFamily = newsTitleFontFamily,
                        color = colorResource(id = newsTitleColor)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    ItemDateTimeLikeShare(
                        publishedDate = item.publishedDate ?: "",
                        likes = "2.7K",
                    )
                    /*Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.publishedDate ?: "",
                            overflow = TextOverflow.Ellipsis,
                            fontSize = publishedDateFontSize,
                            color = colorResource(id = publishedDateColor),
                            fontFamily = publishedDateFontFamily,
                        )

                        // Example of state to toggle image visibility
                        val (isLiked, setIsLiked) = remember { mutableStateOf(false) }

                        // Example of handling image click
                        val onClick = { setIsLiked(!isLiked) }
                        Row {
                            Image(
                                painter = painterResource(id = if (isLiked) R.drawable.ic_like_active_type1 else R.drawable.ic_like_type1_gray),
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
                                        Toast
                                            .makeText(mContext, "share", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                            )
                        }
                    }*/
                }
            }

            Spacer(modifier = Modifier.size(10.dp))
        }
    }



}