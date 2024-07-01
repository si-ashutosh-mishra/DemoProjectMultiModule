package com.example.photo_listing.presentation.photolist.typetwo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photo_listing.R
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.presentation.theme.Black
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosCorousal(data : PhotoListingItem.Carousel,
                   @DrawableRes likeLogo: Int? =null,
                   @DrawableRes clockLogo: Int?=null,
                   @DrawableRes shareLogo: Int?=null,
                   displayReaction: Boolean = false,
                   corousalTitleStyle: TextStyle,
                   corousalPhotosNumberStyle: TextStyle,
                   timeTitleStyle: TextStyle,
                   @DrawableRes corousalBackgroundImage: Int?=null,
                   @ColorRes corousalBackgroundColor: Int? =null,
                   corousalborderColorStyle : TextStyle,
                   corousalReactionTextStyle : TextStyle,
                   activeColorIndicator : Color?=null,
                   inactiveColorIndicator : Color?=null){
    val pagerState = rememberPagerState(pageCount = { data.items.size })
    
    LaunchedEffect(key1 = Unit) {
        while (true){
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    Box(modifier = Modifier.background(if (corousalBackgroundColor!=null) colorResource(id = corousalBackgroundColor) else Color.Transparent)) {

        if (corousalBackgroundImage != null) {
            Image(
                painterResource(id = corousalBackgroundImage),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                HorizontalPager(
                    state = pagerState, modifier = Modifier
                        .fillMaxWidth()
                ) { currentPage ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp)
                    ) {

                        Card(shape = RoundedCornerShape(5.dp)) {
                            Box(contentAlignment = Alignment.BottomStart) {
                                    AsyncImage(
                                        model = data.items.get(currentPage).bannerImageUrl,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(2f)
                                    )

                                    HorizontalPagerIndicator(
                                        pagerState = pagerState,
                                        pageCount = data.items.size,
                                        activeColor = activeColorIndicator ?: Color.Yellow,
                                        inactiveColor = inactiveColorIndicator ?: Color.Gray,
                                        modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 5.dp)
                                    )
                            }
                        }
                        Text(
                            text = data.items.get(currentPage).title ?: "",
                            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp),
                            style = corousalTitleStyle
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(0.dp, 0.dp, 0.dp, 10.dp)
                        ) {

                            Text(
                                text = data.items.get(currentPage).albumCount.toString()+" Photos",
                                style = corousalPhotosNumberStyle
                            )

                            if (clockLogo != null) {
                                Icon(
                                    painter = painterResource(id = clockLogo),
                                    contentDescription = null,
                                    modifier = Modifier.padding(5.dp, 0.dp, 1.dp, 0.dp)
                                )
                            }

                            Text(
                                text = data.items.get(currentPage).beautifiedDuration.toString(),
                                style = timeTitleStyle
                            )

                            if (displayReaction) {
                                Text(
                                    text = "|",
                                    style = corousalborderColorStyle
                                )

                                if (likeLogo != null) {
                                    Icon(
                                        painter = painterResource(id = likeLogo),
                                        contentDescription = null
                                    )
                                }
                                Text(
                                    text = data.items.get(currentPage).reactCount.toString(),
                                    style = corousalReactionTextStyle
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (shareLogo != null) {
                                    Icon(
                                        painter = painterResource(id = shareLogo),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(0.dp, 0.dp, 5.dp, 0.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}