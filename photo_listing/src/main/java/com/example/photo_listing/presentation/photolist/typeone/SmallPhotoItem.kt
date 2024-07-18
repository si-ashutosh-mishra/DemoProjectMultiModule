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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.R
import com.example.photo_listing.presentation.theme.Black
import com.example.photo_listing.presentation.theme.Grey
import com.example.photo_listing.presentation.theme.Purple
import com.example.photo_listing.presentation.theme.White

@Composable
fun SmallPhotoItem(
    assetItem: AssetItem,
    matchPhotoListingTitleStyle: TextStyle,
    reactionTextStyle: TextStyle,
    @DrawableRes reactionIcon: Int? = null,
    @DrawableRes matchShareLogo: Int? = null,
    )
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = assetItem.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .padding(8.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopStart
            )

            Column(
                modifier = Modifier
                    .weight(0.7f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Text(
                    text = "KKR Tags",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Purple,
                    modifier = Modifier
                        .background(
                            color = Grey,
                            shape = RoundedCornerShape(30)
                        )
                        .padding(
                            horizontal = 5.dp,
                            vertical = 2.dp
                        )
                )

                Spacer(
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                )

                Text(
                    text = assetItem.assetTitle.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple,
                    maxLines = 2 ,
                    style = matchPhotoListingTitleStyle
                )

                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .fillMaxWidth(),
                    Arrangement.SpaceBetween

                ) {
                    Text(
                        text = assetItem.beautifiedDuration.toString(),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Black
                    )

                    Row(
                        modifier = Modifier
                            .padding(end = 10.dp)
                    ) {

                        if (reactionIcon != null)
                        {
                            Image(
                                painter = painterResource(id = reactionIcon),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Black),
                                modifier = Modifier
                                    .height(12.dp)
                                    .width(12.dp)
                            )
                        }


                        Spacer(
                            modifier = Modifier
                                .padding(end = 4.dp)
                        )

                        Text(
                            text = assetItem.totalReacts ?:"",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = reactionTextStyle
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(end = 2.dp)
                        )

                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(8.dp)
                                .background(Black)
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(end = 2.dp)
                        )

                        if (matchShareLogo != null) {
                            Image(
                                painterResource(id = matchShareLogo),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp)
                            )
                        }
                    }

                }

            }


        }
    }


}