package com.example.feature_squad.presentation.squadfragment.typetwo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
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
    SquadFragmentTypeTwo()
}

@Composable
fun SquadFragmentTypeTwo(
    @DrawableRes playerImage: Int = R.drawable.ic_player,
    firstNameTextStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Left
    ),
    lastNameTextStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left
    ),
    countryNameTextStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Left
    ),
    playerImageModifier: Modifier = Modifier,
    playerDetail: PlayerItem? = null
) {
    ConstraintLayout (
        modifier = Modifier.fillMaxWidth().height(180.dp).padding(bottom = 16.dp)
    )  {
        val (playerImageId, playerDetailCard) = createRefs()

        Column(
            modifier = Modifier.padding(top = 20.dp).height(180.dp)
                .paint(
                    painterResource(R.drawable.lakr_bg_squad_players),
                    contentScale = ContentScale.FillBounds
                )
                .constrainAs(playerDetailCard) {}
                .padding(16.dp)
        ) {
            Box {
                Text(
                    text = playerDetail?.firstName?: "Ankush", modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    style = firstNameTextStyle,

                )
//                if (playerDetail?.overseasPlayer == true)
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterEnd),
                        painter = painterResource(R.drawable.ic_overseas),
                        contentDescription = ""
                    )
            }

            Text(
                text = playerDetail?.lastName ?: "Yadav",
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                style = lastNameTextStyle
            )

            Row (
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Image(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .size(20.dp),
                    painter = painterResource(R.drawable.lakr_bg_squad_players),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = (playerDetail?.lastName ?: "India").uppercase(),
                    modifier = Modifier.fillMaxWidth(),
                    style = countryNameTextStyle
                )
            }
        }

        AsyncImage(
//            painter = painterResource(playerImage),
            model = playerDetail?.playerImageUrl,
            modifier = playerImageModifier
                .constrainAs(playerImageId) {
                    bottom.linkTo(playerDetailCard.bottom)
                    end.linkTo(playerDetailCard.end)
                }
                .padding(end = 20.dp)
                .fillMaxHeight(),
            contentScale = ContentScale.FillHeight,
            contentDescription = "",
        )

    }



}

@Composable
fun playerImage(modifier: Modifier, ) {

}