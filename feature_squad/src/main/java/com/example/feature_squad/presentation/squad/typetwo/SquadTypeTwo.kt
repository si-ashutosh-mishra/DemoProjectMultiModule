package com.example.feature_squad.presentation.squad.typetwo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.feature_squad.R
import com.example.feature_squad.business.domain.model.squad.PlayerItem

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SquadTypeTwo()
}

@Composable
fun SquadTypeTwo(
    @DrawableRes playerImage: Int = R.drawable.ic_player,
    playerDetailDrawable: List<Color> = listOf(
        Color(0xFF553878),
        Color(0xFF260E46),
    ),
    firstNameTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    ),
    lastNameTextStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerRoleTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Yellow,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),
    playerImageModifier: Modifier = Modifier.height(200.dp),
    playerDetail: PlayerItem? = null
) {
    ConstraintLayout() {
        val (playerImageId, playerDetailCard, divider) = createRefs()

        AsyncImage(
//                painter = rememberAsyncImagePainter(model = playerDetail?.playerImageUrl),
            model = playerDetail?.playerImageUrl,
            modifier = playerImageModifier
                .constrainAs(playerImageId) {}
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
            contentDescription = "",
        )

        Card(
            modifier = Modifier
                .constrainAs(playerDetailCard) {
                    top.linkTo(playerImageId.bottom)
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = playerDetailDrawable
                        )
                    )
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Box {
                    Text(
                        text = playerDetail?.firstName?: "Ankush", modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        style = firstNameTextStyle
                    )
                    if (playerDetail?.overseasPlayer == true)
                        Image(
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.CenterEnd),
                            painter = painterResource(R.drawable.ic_overseas),
                            contentDescription = ""
                        )
                }
                Text(
                    text = playerDetail?.lastName ?: "Yadav", modifier = Modifier.fillMaxWidth(),
                    style = lastNameTextStyle
                )
                Text(
                    text = playerDetail?.skill ?: "All Rounder", modifier = Modifier.fillMaxWidth(),
                    style = playerRoleTextStyle
                )
            }
        }
        Box(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(playerImageId.bottom)
                    bottom.linkTo(playerDetailCard.top)
                },
        ) {
            Divider(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .align(Alignment.Center),
                thickness = 4.dp,
                color = Color(0xFFF2C029)
            )
            if (playerDetail?.isCaptain == true)
                Image(
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_captain),
                    contentDescription = ""
                )
        }
    }

}