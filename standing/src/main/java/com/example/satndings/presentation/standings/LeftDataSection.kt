package com.example.satndings.presentation.standings


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.satndings.business.domain.model.standing.IPLStandings

@Composable
fun LeftDataSection(
    modifier: Modifier,
    firstItem: IPLStandings?,
    list: List<IPLStandings?>,
    topRadius: Dp,
    bottomRadius: Dp,
    titleBarBGColor: Int,
    titleTextStyle: TextStyle,
    teamPosStyle: TextStyle,
    teamNameStyle: TextStyle,
    leftViewBgColor: Int,
    selectedTeamBGColor: Int,
    circularTeamBGColor: Int,
    qualifiedBGColor: Int,
    currentTeamID: Int,

    ) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = topRadius
                    )
                )
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    colorResource(id = titleBarBGColor)
                )
        ) {
            Text(
                text = firstItem?.teamPosition ?: "", style = titleTextStyle
            )
            Text(
                text = firstItem?.title ?: "", style = titleTextStyle
            )
        }
        list.forEachIndexed { index, item ->
            ItemTeamTable(
                iplStandings = item ?: return@forEachIndexed,
                index = index,
                listSize = list.size,
                leftViewBgColor = leftViewBgColor,
                selectedTeamBGColor = selectedTeamBGColor,
                qualifiedBGColor = qualifiedBGColor,
                circularTeamBGColor = circularTeamBGColor,
                bottomRadius = bottomRadius,
                currentTeamID = currentTeamID,
                teamPosStyle = teamPosStyle,
                teamNameStyle = teamNameStyle
            )
        }
    }
}