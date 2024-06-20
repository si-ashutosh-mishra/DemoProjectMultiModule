package com.example.feature_squad.presentation.fixture.typetwo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_squad.R
import java.time.format.TextStyle

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SquadTypeTwo()
}

@Composable
fun SquadTypeTwo(
    @DrawableRes playerImage: Int = R.drawable.ic_player,

) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(all = 8.dp),
    ) {
        Column {
            Image(
                painter = painterResource(playerImage),
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
                contentDescription = "",
            )

            Divider(
                modifier = Modifier.padding(horizontal = 30.dp),
                thickness = 4.dp,
                color = Color.Yellow

            )
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),

                ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Hello", modifier = Modifier,
                    )
                    Text(
                        text = "Hello", modifier = Modifier,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Hello", modifier = Modifier,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }

        }
    }

}