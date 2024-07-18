package com.example.photo_listing.presentation.photolist.typetwo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photo_listing.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestingViews() {
    Column(Modifier.fillMaxWidth()) {

        Text(text = "Match 30: SKNP vs TKR CPL 2021")

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

            Spacer(modifier = Modifier.width(16.dp))

            Image(painter = painterResource(id = R.drawable.ic_happy_face),
                contentDescription ="" )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "0", fontSize = 12.sp)


            Image(painter = painterResource(id = R.drawable.ic_celebration),
                contentDescription ="" )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "0", fontSize = 12.sp)
            
        }

        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.wrapContentHeight()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(bottom = 25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_play_btn),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.6f),
                    contentScale = ContentScale.FillBounds
                )



                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { }) {
                        Image(painter = painterResource(id = R.drawable.ic_swipe_left), contentDescription = "")
                    }

                    IconButton(onClick = {}) {
                        Image(painter = painterResource(id = R.drawable.ic_swipe_right), contentDescription = "")
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

                Text(
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
        }
    }
}