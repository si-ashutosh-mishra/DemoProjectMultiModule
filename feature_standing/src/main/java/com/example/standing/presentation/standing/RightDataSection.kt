package com.example.standing.presentation.standing

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.standing.business.domain.model.standing.IPLStandings

@Composable
fun RightDataSection(
    modifier: Modifier,
    currentTeamID: Int?,
    list: List<IPLStandings?>,
    topRadius: Dp,
    bottomRadius: Dp,
    titleBarBGColor: Color,
    rightViewBgColor: Color,
    selectedTeamBGColor: Color,
    titleTextStyle: TextStyle,
    valueTextStyle: TextStyle,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topEnd = topRadius, bottomEnd = bottomRadius
                )
            )
            .horizontalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
        ) {
            list.forEachIndexed { index, item ->
                ItemTeamValueTable(
                    index = index,
                    titleBarBGColor = titleBarBGColor,
                    rightViewBgColor = rightViewBgColor,
                    iplStanding = item ?: return@forEachIndexed,
                    titleTextStyle = titleTextStyle,
                    valueTextStyle = valueTextStyle,
                    currentTeamID = currentTeamID,
                    selectedTeamBGColor = selectedTeamBGColor,
                )
            }
        }
    }


}