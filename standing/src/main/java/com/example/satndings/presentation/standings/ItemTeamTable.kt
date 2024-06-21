package com.example.satndings.presentation.standings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.satndings.business.domain.model.standing.IPLStandings

@Composable
fun ItemTeamTable(
    iplStandings: IPLStandings,
    index: Int,
    listSize: Int,
    leftViewBgColor: Int,
    bottomRadius: Dp,
    selectedTeamBGColor: Int,
    qualifiedBGColor: Int,
    circularTeamBGColor: Int,
    currentTeamID: Int,
    teamPosStyle: TextStyle,
    teamNameStyle: TextStyle
) {

    val shape = if (index == listSize - 1) RoundedCornerShape(
        bottomStart = bottomRadius
    ) else RoundedCornerShape(
        0.dp, 0.dp, 0.dp, 0.dp
    )
    Row(
        modifier = Modifier
            .clip(
                shape = shape
            )
            .fillMaxWidth()
            .height(50.dp)
            .background(
                colorResource(id = if (currentTeamID == iplStandings.teamID) selectedTeamBGColor else leftViewBgColor)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(20.dp)
                    .background(
                        color = if (iplStandings.teamIsQualified) colorResource(id = qualifiedBGColor) else Color.Transparent
                    )
                    .padding(top = 2.dp),
                text = if (iplStandings.teamIsQualified) "Q" else iplStandings.teamPosition ?: "",
                style = teamPosStyle,
            )
        }
        Box(
            modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = iplStandings.teamLogoUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(shape = CircleShape)
                    .background(
                        color = colorResource(id = circularTeamBGColor)
                    )
                    .padding(all = 5.dp),

                )
        }
        Text(
            text = iplStandings.title ?: "",
            style = teamNameStyle,
            modifier = Modifier
                .weight(1.5f)
                .padding(start = 8.dp)
        )
    }
}