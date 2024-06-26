package com.example.standing.data.model.standings


import com.google.gson.annotations.SerializedName


data class StandingEntity(
    @SerializedName("team_list") val teamList: List<Team>?,
    @SerializedName("show_form") val isShowForm: Boolean = false,
    @SerializedName("is_swap_required") val isSwapRequired: Boolean = false,
    @SerializedName("team_count") val requiredTeamCount: Int? = null,
    @SerializedName("swap_pos") val swapPosition: Int? = null,
    @SerializedName("team_id") val currentTeamId: Int? = null
)

data class Team(
    @SerializedName("matches") val matches: List<Match>?,
    @SerializedName("id") val id: String?,
    @SerializedName("is_qualified") val isQualified: String?,
    @SerializedName("l") val l: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("nr") val nr: String?,
    @SerializedName("nrr") val nrr: String?,
    @SerializedName("p") val p: String?,
    @SerializedName("pos") val pos: String?,
    @SerializedName("position_status") val positionStatus: String?,
    @SerializedName("prev_position") val prevPosition: String?,
    @SerializedName("pts") val pts: String?,
    @SerializedName("short_name") val shortName: String?,
    @SerializedName("t") val t: String?,
    @SerializedName("w") val w: String?
)