package com.example.photo_listing.presentation.photolist.typetwo.photodetails

import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.photo_listing.R
import com.example.photo_listing.business.model.PhotoItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosDetailsCorousal(
    data: List<PhotoItem>?,
    @DrawableRes likeLogo: Int? = null,
    @DrawableRes clockLogo: Int? = null,
    @DrawableRes shareLogo: Int? = null,
    displayReaction: Boolean = false,
    corousalTitleStyle: TextStyle,
    corousalPhotosNumberStyle: TextStyle,
    timeTitleStyle: TextStyle,
    @DrawableRes corousalBackgroundImage: Int? = null,
    @ColorRes corousalBackgroundColor: Int? = null,
    corousalborderColorStyle: TextStyle,
    corousalReactionTextStyle: TextStyle,
    activeColorIndicator: Color? = null,
    inactiveColorIndicator: Color? = null
) {
    val pagerState = rememberPagerState(pageCount = { data?.size ?: 0 })

    /*LaunchedEffect(key1 = Unit) {
        while (true){
           // delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }*/
    Box(
        modifier = Modifier.background(if (corousalBackgroundColor != null) colorResource(id = corousalBackgroundColor) else Color.Transparent)
            .padding(top = 10.dp, bottom = 10.dp)) {

        if (corousalBackgroundImage != null) {
            Image(
                painterResource(id = corousalBackgroundImage),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
        }

        Column(Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp)) {

            Text(text = "Match 30: SKNP vs TKR CPL 2021",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())

            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock), // Replace with your clock icon
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "9m", fontSize = 12.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_like), // Replace with your views icon
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                androidx.compose.material.Text(text = "0", fontSize = 12.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_happy_inactive),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "0", fontSize = 12.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_celebration_inactive),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                androidx.compose.material.Text(text = "0", fontSize = 12.sp)

            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                HorizontalPager(
                    state = pagerState, modifier = Modifier
                        .fillMaxWidth()
                ) { currentPage ->

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(bottom = 25.dp)
                    ) {

                        Log.d(
                            "IMAGE_URL",
                            "PhotosDetailsCorousal: " + data?.get(currentPage)?.imageUrl
                        )
                        AsyncImage(
                            model = data?.get(currentPage)?.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.6f)
                        )


                        Row(
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = { }) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_swipe_left),
                                    contentDescription = ""
                                )
                            }

                            IconButton(onClick = {}) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_swipe_right),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    androidx.compose.material.Text(
                        text = "1/8 Photos",
                        modifier = Modifier.background(Color.Yellow)
                    )


                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = ""
                        )
                    }
                }
                // }
                //}
            }
        }
    }
}