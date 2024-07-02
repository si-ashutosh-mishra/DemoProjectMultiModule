package com.example.feature_news.presentation.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_news.R
import com.example.feature_news.business.domain.model.news.NewsListingItem
import com.example.feature_news.presentation.news.typeone.CarouselTypeOneScreen
import com.example.feature_news.presentation.news.typeone.NewsItemViewType
import com.example.feature_news.presentation.news.typetwo.LazyColumnWithMultipleItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsTypeOne(
    onViewMoreClick: () -> Unit,
) {

    val viewModel: NewsViewModel = hiltViewModel()

    val list by viewModel.items.observeAsState(initial = emptyList())

    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.fetchData("https://www.knightclub.in/apiv4/gettemplatedata?url=kkr-app-home/app-news&is_app=1")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {

        /*itemsIndexed(
            items = list,
            key = { _: Int, item: NewsListingItem ->
                item.type.id
            },
        ) { _, item ->*/
        list.forEach {item ->
            when (item.type.id) {
                NewsItemViewType.CAROUSEL.id -> {
                    (item as NewsListingItem.Carousel).let { items ->
                        val count = items.items.size
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(330.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            val pagerState =
                                rememberPagerState(initialPage = 0, 0f) { count }

                            HorizontalPager(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(330.dp),
                                state = pagerState,
                                pageSpacing = 0.dp,
                                userScrollEnabled = true,
                                reverseLayout = false,
                                contentPadding = PaddingValues(0.dp),
                                beyondBoundsPageCount = 0,
                                key = null,
                                pageContent = {
                                    CarouselTypeOneScreen(
                                        item = items.items[it]
                                    )
                                })
                            Row(
                                Modifier
                                    .padding(bottom = 20.dp)
                                    .wrapContentHeight()
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                repeat(count) { iteration ->
                                    val color =
                                        if (pagerState.currentPage == iteration) Color.Yellow else Color.LightGray
                                    if (pagerState.currentPage == iteration) {
                                        Box(
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .clip(shape = RoundedCornerShape(10.dp))
                                                .background(color)
                                                .width(22.dp)
                                                .height(8.dp)
                                        )
                                    } else {
                                        Box(
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .clip(CircleShape)
                                                .background(color)
                                                .size(8.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                        )
                    }
                }

                NewsItemViewType.BANNER.id -> {
                    (item as NewsListingItem.Banner).let {
                        NewsBanner(banner = it)
                    }
                }

                NewsItemViewType.LATEST.id -> {
                    (item as NewsListingItem.Latest).let { item ->
                        ItemCategoryArticle(
                            title = item.title,
                            titleFontSize = 18.sp,
                            titleFontStyle = FontFamily(Font(R.font.rubik_medium)),
                            assetList = item.items,
                            clickViewAll = onViewMoreClick
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                        )
                    }
                }

                NewsItemViewType.FEATURED.id -> {
                    (item as NewsListingItem.Featured).let { item ->
                        ItemCategoryArticle(
                            title = item.title,
                            titleFontSize = 18.sp,
                            titleFontStyle = FontFamily(Font(R.font.rubik_medium)),
                            assetList = item.items,
                            clickViewAll = onViewMoreClick
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                        )
                    }
                }
            }
        }
    }
}