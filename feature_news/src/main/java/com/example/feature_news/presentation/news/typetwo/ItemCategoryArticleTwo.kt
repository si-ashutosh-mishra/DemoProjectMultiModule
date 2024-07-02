package com.example.feature_news.presentation.news.typetwo

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature_news.R
import com.example.feature_news.presentation.theme.black
import com.example.feature_news.presentation.theme.black_90
import com.example.feature_news.presentation.theme.light_gray
import com.example.feature_news.presentation.theme.purple
import com.example.feature_news.presentation.theme.textcolor_gray
import com.example.feature_news.presentation.theme.white
import com.example.lb_content_listing.business.domain.model.AssetItem

@Composable
fun ItemCategoryArticleTwo(
    assetList: List<AssetItem>,
    @ColorRes cardColor: Color = black,
    tagFontSize: TextUnit = 10.sp,
    @ColorRes tagColor: Color = black_90,
    @ColorRes tagTextColor: Color = white,
    tagRadius: Dp = 20.dp,
    tagFontStyle: FontFamily = FontFamily(Font(R.font.rubik_medium)),
    newsTitleFontSize: TextUnit = 12.sp,
    newsTitleFontFamily: FontFamily = FontFamily(Font(R.font.urbanist_bold)),
    @ColorRes newsTitleColor : Color = white,
) {
    val dataList = listOf("Abhishek", "Harshit", "Gaurav", "Surbhi", "Swati")

    /*Card(
        modifier = Modifier
            .padding(12.dp)
        *//*.clickable {
            cardClick(assetItem?.titleAlias ?: "")
        }*//*,
        shape = RoundedCornerShape(4.dp), elevation = 4.dp
    ) {*/
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        //.clip(shape = RoundedCornerShape(10.dp))
    ) {
        dataList.forEach {
            Card(
                /*modifier = Modifier
                    .padding(12.dp)*/
                /*.clickable {
                    cardClick(assetItem?.titleAlias ?: "")
                }*/
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(cardColor)
                        .fillMaxWidth()
                ) {
                    Column {
                        Box() {
                            AsyncImage(
                                model = "https://www.imgacademy.com/sites/default/files/styles/scale_1700w/public/2022-07/img-homepage-meta_0.jpg?itok=LMirU0Ik",
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(165.dp)
                                    .height(124.dp)
                                //.clip(RoundedCornerShape(10.dp)),
                                //placeholder = painterResource(id = androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
                            )
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 10.dp, horizontal = 10.dp)
                                    .align(Alignment.BottomStart)
                            ) {
                                Text(
                                    color = tagTextColor,
                                    fontSize = tagFontSize,
                                    text = "News",
                                    fontFamily = tagFontStyle,
                                    modifier = Modifier
                                        .background(
                                            color = tagColor,
                                            shape = RoundedCornerShape(tagRadius)
                                        )
                                        .padding(
                                            start = 10.dp,
                                            end = 10.dp,
                                            top = 2.dp,
                                            bottom = 2.dp
                                        )

                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = newsTitleFontSize,
                            fontFamily = newsTitleFontFamily,
                            color = newsTitleColor
                        )
                        ItemInfoTwo(
                            modifier = Modifier.weight(1f).fillMaxSize(),
                            publishedDate = "1m",
                            likes = "1k"
                        )
                    }

                }
            }

        }
    }
    //}

    /*LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 0.dp),
        //modifier = Modifier.weight(1f)
    ) {

        items(dataList){
            Text(text = it)
        }
    }*/
}

@Composable
fun LazyColumnWithMultipleItem() {
    var list by remember {
        mutableStateOf(
            listOf(
                "Android",
                "iOS",
                "Flutter",
                "Kotlin",
                "Swift",
                "Java",
                "C++"
            )
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item {
            Text(text = "Public leagues", modifier = Modifier.padding(top = 16.dp))
        }
        items(items = list) {
            Text(
                text = "Programming Language $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f))
                    .padding(24.dp)
            )
        }
        item {
            Text(text = "Private leagues")
        }
        items(items = list) {
            Text(
                text = "Programming Language $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f))
                    .padding(24.dp)
            )
        }
        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                onClick = {
                    list = list.shuffled()
                }
            ) {
                Text(text = "Button")
            }
        }
    }
}