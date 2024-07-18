package com.example.feature_video_listing.presentation.videolist.typetwo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_video_listing.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoThumbnail() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidth = (screenWidth / 4) + 170.dp

    Card(
        modifier = Modifier
            .wrapContentSize()
            .width(itemWidth)
            .aspectRatio(1.4f),
    ) {
        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_play_btn), // Replace with your thumbnail image
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(shape = MaterialTheme.shapes.medium)
                )
                IconButton(
                    onClick = { /* TODO: Handle play button click */ },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color.Black.copy(alpha = 0.6f), shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play_btn), // Replace with your play icon
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Column {
                Text(
                    text = "ONE FINAL PUSH TOWARDS GLORY FOR THE KNIGHTS",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
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
                    Text(text = "9m", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like), // Replace with your views icon
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "0", fontSize = 12.sp)
                }
            }
        }
    }
}



@Composable
fun VideoThumbnailPreview() {
    VideoThumbnail()
}
