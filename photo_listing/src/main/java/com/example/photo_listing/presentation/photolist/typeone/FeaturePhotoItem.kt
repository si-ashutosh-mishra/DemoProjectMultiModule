package com.example.photo_listing.presentation.photolist.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.R
import com.example.photo_listing.presentation.theme.Yellow

@Composable
fun FeaturePhotoItem(
    assetItem: AssetItem,
    matchPhotoListingTitleStyle: TextStyle,
    reactionTextStyle: TextStyle,
    @DrawableRes reactionIcon: Int? = null,
    @DrawableRes matchShareLogo: Int? = null,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = assetItem.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillHeight
        )
        Image(
            painter = painterResource(id = R.drawable.bg_gradient),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 6.dp)
        ) {

            Text(
                text = "KKR Tags",
                color = Yellow,
                modifier = Modifier
                    .background(
                        color = Black,
                        shape = RoundedCornerShape(30)
                    )
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = assetItem.assetTitle.toString(),
                color = White,
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                style = TextStyle()
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = assetItem.beautifiedDuration.toString(),
                    color = White,
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier
                        .padding(end = 14.dp)
                ) {

                    if (reactionIcon != null)
                        Icon(
                            painterResource(id = reactionIcon),
                            contentDescription = null,
                            modifier = Modifier
                                .width(12.dp)
                                .height(12.dp)
                        )

                    Spacer(
                        modifier = Modifier
                            .padding(end = 3.dp)
                    )
                    Text(
                        text = assetItem.totalReacts ?: "",
                        color = White,
                        modifier = Modifier,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        style = reactionTextStyle
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(end = 3.dp)
                    )

                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(8.dp)
                            .background(color = White)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(end = 3.dp)
                    )

                    if (matchShareLogo != null) {
                        Image(
                            painterResource(id = matchShareLogo),
                            contentDescription = null,
                            modifier = Modifier
                                .width(12.dp)
                                .height(12.dp)
                        )
                    }


                }

            }
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }

}