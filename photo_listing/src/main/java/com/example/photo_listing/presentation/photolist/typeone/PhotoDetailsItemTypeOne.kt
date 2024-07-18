package com.example.photo_listing.presentation.photolist.typeone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.presentation.theme.White

@Composable
fun ListingItemGridLayout(
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

)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = White,
                RoundedCornerShape(10.dp)
            )
    )
    {

        Image(
            painter = rememberAsyncImagePainter(model = assetItem.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp
                    )
                ),
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .padding(bottom = 15.dp)
        )

        Text(
            text = assetItem.assetTitle.toString().uppercase(),
            modifier = Modifier
                .padding(horizontal = 8.dp),
            maxLines = 2,
            style = matchPhotoListingTitleStyle
        )

        Spacer(
            modifier = Modifier
                .padding(bottom = 15.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally),

            ) {

            if (matchClockIcon != null)
                Icon(
                    painter = painterResource(id = matchClockIcon),
                    contentDescription = "Duration Icon"
                )
            Spacer(
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Text(
                text = assetItem.beautifiedDuration ?: "",
                style = matchTimeTextStyle
            )
            Spacer(
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            if (displayMatchReaction) {
                Text(
                    text = "|", modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 0.dp),
                    style = borderColorStyle
                )

                if (reactionIcon != null) {
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

            Spacer(modifier = Modifier.weight(1f))
            if (matchShareLogo != null) {
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

//)
//@Composable
//fun PreviewScreen() {
//    ListingItemGridLayout(
//        photoId = R.drawable.img,
//        photoTitle = "Iyer’s half-century in vain as KL Rahul powers Punjab to a five-wicket win",
//        photoDurationIcon = R.drawable.ic_published_duration,
//        photoDuration = "9m",
//        photoLikeIcon = R.drawable.ic_like,
//        photoLikeCount = "45",
//        photoShareIcon = R.drawable.ic_share_v2
//    )
//}

