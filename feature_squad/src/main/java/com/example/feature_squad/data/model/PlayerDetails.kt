package com.example.feature_squad.data.model

import com.google.gson.annotations.SerializedName

data class PlayerDetails(
    @SerializedName("id")
    val id: String?,
    @SerializedName("is_captain")
    val isCaptain: Boolean?,
    @SerializedName("is_vice_captain")
    val isViceCaptain: Boolean?,
    @SerializedName("is_wicket_keeper")
    val isWicketKeeper: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_english")
    val nameEnglish: String?,
    @SerializedName("national_team")
    val nationalTeam: Any?,
    @SerializedName("national_team_id")
    val nationalTeamId: Any?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("nationality_id")
    val nationalityId: String?,
    @SerializedName("skill")
    val skill: String?,
    @SerializedName("skill_id")
    val skillId: String?
)