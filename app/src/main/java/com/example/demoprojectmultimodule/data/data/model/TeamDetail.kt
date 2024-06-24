package com.example.demoprojectmultimodule.data.data.model

import com.google.gson.annotations.SerializedName

data class TeamDetail(
    @SerializedName("series_id")
    val seriesId: String,
    @SerializedName("team_id")
    val teamId: String,
    @SerializedName("tour_id")
    val tourId: String
)