package com.example.feature_news.presentation.news.typeone

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.content_listing.business.domain.model.AssetItem
import com.example.feature_news.R
import com.example.feature_news.presentation.theme.light_gray
import com.example.feature_news.presentation.theme.purple
import com.example.feature_news.presentation.theme.textcolor_gray

@Composable
fun ItemCategoryArticle(
    title: String,
    titleFontSize: TextUnit,
    titleFontStyle: FontFamily,
    assetList: List<AssetItem>,
    tagFontSize: TextUnit = 10.sp,
    @ColorRes tagColor: Color =  light_gray,
    @ColorRes tagTextColor: Color =  purple,
    tagRadious: Dp = 20.dp,
    tagFontStyle: FontFamily = FontFamily(Font(R.font.rubik_medium)),
    newsTitleFontSize: TextUnit = 14.sp,
    newsTitleFontFamily: FontFamily = FontFamily(Font(R.font.rubik_regular)),
    @ColorRes newsTitleColor : Color = textcolor_gray,
    clickViewAll: () -> Unit
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

        Text(text = "View All".uppercase(), fontSize = 13.sp, modifier = Modifier.clickable {
            clickViewAll()
        })
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
                        color = tagTextColor,
                        fontSize = tagFontSize,
                        text = item.assetTitle ?: "tag",
                        fontFamily = tagFontStyle,
                        modifier = Modifier
                            .background(
                                color = tagColor,
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
                        color = newsTitleColor
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    ItemDateTimeLikeShare(
                        publishedDate = item.publishedDate ?: "",
                        likes = "2.7K",
                    )
                }
            }

            Spacer(modifier = Modifier.size(10.dp))
        }
    }



}