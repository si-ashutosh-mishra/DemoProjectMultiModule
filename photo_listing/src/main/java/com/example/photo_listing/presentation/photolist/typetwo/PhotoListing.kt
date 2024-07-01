package com.example.photo_listing.presentation.photolist.typetwo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.presentation.theme.Black

@Composable
fun PhotoListing(
    data : PhotoListingItem.PhotosArticle,
    @DrawableRes matchPhotoBackgroundImage : Int?=null,
    @ColorRes matchPhotoBackgroundColor : Int?=null,
    matchPhotoTitleStyle: TextStyle,
    matchMoreButton: ButtonColors,
    matchPhotoListingTitleStyle : TextStyle,
    @DrawableRes matchClockIcon : Int?=null,
    matchTimeTextStyle : TextStyle,
    displayMatchReaction : Boolean = false,
    borderColorStyle : TextStyle,
    @DrawableRes reactionIcon : Int?=null,
    reactionTextStyle : TextStyle,
    @DrawableRes matchShareLogo : Int?=null,
    matchPhotosNumberStyle: TextStyle,
    matchMoreButtonTextStyle : TextStyle,
){
   // Box (){

    Box(modifier = Modifier.background(if (matchPhotoBackgroundColor!=null) colorResource(id = matchPhotoBackgroundColor) else Color.Transparent)){

        if (matchPhotoBackgroundImage!=null){
            Image(painterResource(id = matchPhotoBackgroundImage),
                contentDescription ="",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize())
        }

        Column(modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .padding(0.dp, 5.dp, 0.dp, 5.dp)) {

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.title,
                    style = matchPhotoTitleStyle,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                )

                Button(
                    onClick = { },
                    colors = matchMoreButton,
                    modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp).height(30.dp)
                        .width(80.dp).align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "More",
                        style = matchMoreButtonTextStyle
                    )
                }
            }

            LazyRow {
                items(data.items) {
                    ListingOfPhotos(
                        it,
                        data.items.size,
                        matchPhotoListingTitleStyle,
                        matchClockIcon,
                        matchTimeTextStyle,
                        displayMatchReaction,
                        borderColorStyle,
                        reactionIcon,
                        reactionTextStyle = reactionTextStyle,
                        matchShareLogo,
                        matchPhotosNumberStyle
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayPhotosHorizontally(){

}

@Composable
fun DisplayPhotosVertically(){

}
//}