package com.example.photo_listing.presentation.photolist.typetwo

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.photo_listing.R


@Composable
fun ListingOfPhotos(assetItem: AssetItem,itemCounts : Int) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidth = (screenWidth / itemCounts) + 70.dp

    Card (modifier = Modifier
        .width(itemWidth)
        .aspectRatio(0.8f)
        .padding(0.dp, 10.dp, 10.dp, 0.dp)){

        Column(verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize().wrapContentHeight()) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f),
                contentAlignment = Alignment.BottomStart){
                Image(
                    painter = rememberAsyncImagePainter(model = assetItem.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                    )

                Text(text = "${assetItem.totalAssets} Photos",
                    color = Color.White,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(5.dp)
                        .background(Color(0x80000000))
                , textAlign = TextAlign.Left)
            }

            Text(
                text = assetItem.assetTitle.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Left,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(5.dp,0.dp,1.dp,5.dp)
            )

            Row(verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxHeight()) {

                Icon(painter = painterResource(id = R.drawable.ic_clock), contentDescription = null,
                    modifier = Modifier.padding(5.dp,0.dp,1.dp,7.dp))

                Text(text = assetItem.beautifiedDuration?: "",modifier = Modifier.padding(0.dp,0.dp,1.dp,5.dp) )

                Text(text = "|",modifier = Modifier.padding(2.dp,0.dp,2.dp,5.dp) )

                Icon(painter = painterResource(id = R.drawable.ic_like),
                    contentDescription =null, modifier = Modifier.padding(0.dp,0.dp,1.dp,7.dp) )

                Text(text = assetItem.totalReacts ?:"",modifier = Modifier.padding(0.dp,0.dp,1.dp,5.dp) )

                Icon(painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 1.dp, 7.dp)
                        .fillMaxWidth(1f))
            }
        }
    }
}

