package com.example.feature_squad.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerStats(
    val matches: String? = "0",
    val runs: String? = "0",
    val wickets: String? = "0",
): Parcelable

enum class SkillEnum(val skillId: String) {
    ALL(skillId = "0"),
    BATSMAN(skillId = "1"),
    BOWLER(skillId = "2"),
    ALL_ROUNDER(skillId = "3"),
    WICKET_KEEPER(skillId = "4")
}
