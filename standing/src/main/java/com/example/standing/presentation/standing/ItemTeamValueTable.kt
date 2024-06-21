package com.example.standing.presentation.standing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.standing.business.domain.model.standing.IPLStandings


@Composable
fun ItemTeamValueTable(
    index: Int,
    titleBarBGColor: Int,
    rightViewBgColor: Int,
    iplStanding: IPLStandings,
    titleTextStyle: TextStyle,
    valueTextStyle: TextStyle,
    currentTeamID: Int?,
    selectedTeamBGColor: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .background(
                if (index == 0) colorResource(id = titleBarBGColor) else if (currentTeamID == iplStanding.teamID) colorResource(
                    id = selectedTeamBGColor
                ) else colorResource(id = rightViewBgColor)
            ),
    ) {
        iplStanding.scoreList.forEachIndexed { _, s ->
            Text(
                modifier = Modifier.width(50.dp),
                text = s ?: "",
                style = if (index == 0) titleTextStyle else valueTextStyle
            )
        }
        if (!iplStanding.lastMatchesResult.isNullOrEmpty() && iplStanding.isShowForm) {
            if (index == 0) {
                Text(
                    modifier = Modifier.width(151.dp),
                    text = iplStanding.lastMatchesResult.firstOrNull() ?: "",
                    style = titleTextStyle
                )
            } else {
                WinLoseData(
                    iplStanding.lastMatchesResult
                )
            }

        }

    }
}

@Composable
fun WinLoseData(list: List<String?>) {
    Row {
        list.forEachIndexed { _, s ->
            ItemWinLose(s)
        }
    }
}