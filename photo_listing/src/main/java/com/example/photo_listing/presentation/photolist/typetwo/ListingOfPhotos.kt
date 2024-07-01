package com.example.photo_listing.presentation.photolist.typetwo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.presentation.theme.Black

@Composable
fun ListingOfPhotos(assetItem: AssetItem,
                    itemCounts : Int,
                    matchPhotoListingTitleStyle : TextStyle,
                    @DrawableRes matchClockIcon : Int?=null,
                    matchTimeTextStyle : TextStyle,
                    displayMatchReaction : Boolean = false,
                    borderColorStyle : TextStyle,
                    @DrawableRes reactionIcon : Int?=null,
                    reactionTextStyle : TextStyle,
                    @DrawableRes matchShareLogo : Int?=null,
                    matchPhotosNumberStyle: TextStyle) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidth = (screenWidth / itemCounts) + 80.dp

    Card (modifier = Modifier
        .width(itemWidth)
        .aspectRatio(0.8f)
        .padding(5.dp, 10.dp, 10.dp, 0.dp)){

        Column(verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()) {

            Box(contentAlignment = Alignment.BottomStart){

                Image(painter = rememberAsyncImagePainter(model = assetItem.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1.2f),
                    contentScale = ContentScale.Crop
                    )

                Text(text = "${assetItem.totalAssets} Photos",
                    style = matchPhotosNumberStyle)
            }

            Text(
                text = assetItem.assetTitle.toString(),
                style = matchPhotoListingTitleStyle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(5.dp,0.dp,1.dp,5.dp)
            )

            Row(verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween) {

                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ,modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                        .fillMaxHeight()){

                    if (matchClockIcon!=null){
                        Icon(
                             painterResource(id = matchClockIcon),
                            contentDescription = null,
                            modifier = Modifier.padding(5.dp, 0.dp, 1.dp, 0.dp)
                        )
                    }

                    Text(
                        text = assetItem.beautifiedDuration ?: "",
                        modifier = Modifier.padding(5.dp, 0.dp, 1.dp, 0.dp),
                        style = matchTimeTextStyle
                    )

                    if (displayMatchReaction) {
                        Text(
                            text = "|", modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 0.dp),
                            style = borderColorStyle
                        )

                        if (reactionIcon!=null) {
                            Icon(
                                painterResource(id = reactionIcon),
                                contentDescription = null,
                                modifier = Modifier.padding(0.dp, 0.dp, 1.dp, 0.dp)
                            )
                        }

                        Text(
                            text = assetItem.totalReacts ?: "",
                            modifier = Modifier.padding(0.dp, 0.dp, 1.dp, 0.dp),
                            style = reactionTextStyle
                        )
                    }

                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    ){

                    if (matchShareLogo!=null) {
                        Icon(
                            painter = painterResource(id = matchShareLogo),
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

