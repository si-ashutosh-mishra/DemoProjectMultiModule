package com.example.feature_video_listing.presentation.videolist.typetwo

import android.graphics.Color.parseColor
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import com.example.feature_video_listing.R
import com.example.lb_content_listing.business.domain.model.AssetItem


@Composable
fun ListingOfShortVideos(
    assetItem: AssetItem,
    itemCounts: Int,
    matchPhotoListingTitleStyle: TextStyle,
    @DrawableRes matchClockIcon: Int? = null,
    matchTimeTextStyle: TextStyle,
    displayMatchReaction: Boolean = false,
    borderColorStyle: TextStyle,
    @DrawableRes reactionIcon: Int? = null,
    reactionTextStyle: TextStyle,
    @DrawableRes matchShareLogo: Int? = null,
    matchPhotosNumberStyle: TextStyle
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidth = (screenWidth / itemCounts) + 80.dp

    Card(
        modifier = Modifier
            .wrapContentSize()
            .width(itemWidth)
            .aspectRatio(0.7f)
            .padding(5.dp, 10.dp, 10.dp, 0.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = assetItem.imageUrl), // Replace with your thumbnail image
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(shape = MaterialTheme.shapes.medium)
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )
                
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color.Black.copy(alpha = 0.6f), shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play_btn), // Replace with your play icon
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Column {
                Text(
                    text = assetItem.assetTitle.toString(),
                    style = matchPhotoListingTitleStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
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
                    Text(
                        text = assetItem.beautifiedDuration.toString(),
                        style = matchTimeTextStyle
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like), // Replace with your views icon
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = assetItem.totalReacts.toString(),
                        style = reactionTextStyle
                    )
                }
            }
        }
    }
}




