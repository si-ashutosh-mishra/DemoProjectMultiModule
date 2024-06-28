package com.example.photo_listing.presentation.photolist.typetwo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photo_listing.R
import com.example.photo_listing.business.model.PhotoListingItem
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosCorousal(data : PhotoListingItem.Carousel){
    val pagerState = rememberPagerState(pageCount = { data.items.size })
    
    LaunchedEffect(key1 = Unit) {
        while (true){
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box (modifier = Modifier.fillMaxWidth()){
            HorizontalPager(state = pagerState
            , modifier = Modifier
                    .fillMaxWidth()) {currentPage->

                Column (modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp,5.dp,5.dp,5.dp)){
                    Box (contentAlignment = Alignment.BottomStart){

                        AsyncImage(model = data.items.get(currentPage).bannerImageUrl
                            , contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().fillMaxHeight())

                        HorizontalPagerIndicator(pagerState = pagerState,
                            pageCount = data.items.size,
                            activeColor = Color.Yellow,
                            inactiveColor = Color.Gray,
                            modifier = Modifier.padding(5.dp,0.dp,0.dp,5.dp)
                        )
                    }

                    Text(text = data.items.get(currentPage).title?: ""
                    , modifier = Modifier.padding(0.dp,10.dp,0.dp,10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxHeight()
                            .padding(0.dp,0.dp,0.dp,10.dp)) {


                        Text(
                            text = "16 Photos",
                            color = Color.White,
                            modifier = Modifier
                                .wrapContentWidth()
                                .background(Color.Blue), textAlign = TextAlign.Left
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = null,
                            modifier = Modifier.padding(5.dp, 0.dp, 1.dp, 0.dp)
                        )

                        Text(
                            text = "12 Min"
                        )

                        Text(
                            text = "|"
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = null
                        )

                        Text(
                            text = "236"
                        )


                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share),
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