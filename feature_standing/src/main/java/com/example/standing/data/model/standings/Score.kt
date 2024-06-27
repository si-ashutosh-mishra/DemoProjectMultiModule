package com.example.standing.data.model.standings


import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("innings")
    val innings: List<Inning?>?,
    @SerializedName("team_id")
    val teamId: String?
)