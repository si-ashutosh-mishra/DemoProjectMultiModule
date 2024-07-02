package com.example.feature_news.presentation.newslist.typeone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.feature_news.R
import com.example.feature_news.presentation.news.NewsToolbar
import com.example.feature_news.presentation.news.typeone.ItemDateTimeLikeShare
import com.example.feature_news.presentation.theme.kkr_purple_dark
import com.example.feature_news.presentation.theme.light_gray
import com.example.feature_news.presentation.theme.purple
import com.example.feature_news.presentation.theme.yellow
import com.example.lb_content_listing.business.domain.model.AssetItem

@Composable
fun NewsListTypeOne() {

    val viewModel: NewsListingViewModel = hiltViewModel()

    viewModel.fetchData("https://www.knightclub.in/apiv4/listing?entities=172,4&otherent=&exclent=&pgnum={page_no}&inum={count}&pgsize={count}")
    val newsItems: LazyPagingItems<AssetItem> =
        viewModel.items.collectAsLazyPagingItems()

    LaunchedEffect(
        key1 = Unit
    ) {

    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        NewsToolbar(
            onBackClick = { },
            onFilterClick = { },
            titleBarIconTintColor = light_gray,
            toolBarColor = kkr_purple_dark,
            toolBarTitle = "News List",
            toolBarTitleTextStyle = TextStyle (
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                //fontFamily = interBold,
                fontWeight = FontWeight.Bold,
            ),
            showBack = true,
            //showFilter = showFilter
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(newsItems.itemCount) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = newsItems.get(it)?.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(165.dp)
                                .height(124.dp)
                                .clip(RoundedCornerShape(10.dp)),
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_video_play),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .height(124.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            color = purple,//tagTextColor,
                            fontSize = 10.sp,//tagFontSize,
                            text = newsItems.get(it)?.primaryEntityDisplayName ?: "",
                            fontFamily = FontFamily(Font(R.font.rubik_medium)),// tagFontStyle,
                            modifier = Modifier
                                .background(
                                    color = light_gray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 14.dp, vertical = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            //modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                            maxLines = 2,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis,
                            text = newsItems.get(it)?.assetTitle ?: ""
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            ItemDateTimeLikeShare(
                                publishedDate = newsItems.get(it)?.publishedDate ?: "",
                                likes = "2.7K",
                            )
                        }
                    }
                }
            }
        }
    }


}



