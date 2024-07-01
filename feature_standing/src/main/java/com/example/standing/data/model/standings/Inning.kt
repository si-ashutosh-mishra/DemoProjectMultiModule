package com.example.standing.data.model.standings


import com.google.gson.annotations.SerializedName

data class Inning(
    @SerializedName("number")
    val number: String?,
    @SerializedName("overs_played")
    val oversPlayed: String?,
    @SerializedName("runs_scored")
    val runsScored: String?,
    @SerializedName("wickets_lost")
    val wicketsLost: String?
)