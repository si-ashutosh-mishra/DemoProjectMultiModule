package com.example.feature_squad.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bowling(
    @SerializedName("average")
    val average: Double?,
    @SerializedName("best")
    val best: String?,
    @SerializedName("boundary_frequency")
    val boundaryFrequency: Double?,
    @SerializedName("boundary_percentage")
    val boundaryPercentage: Double?,
    @SerializedName("dot_ball_frequency")
    val dotBallFrequency: Double?,
    @SerializedName("dot_ball_percentage")
    val dotBallPercentage: Double?,
    @SerializedName("economy")
    val economy: Double?,
    @SerializedName("five_wickets_haul")
    val fiveWicketsHaul: Int?,
    @SerializedName("four_wickets_haul")
    val fourWicketsHaul: Int?,
    @SerializedName("innings")
    val innings: Int?,
    @SerializedName("maidens")
    val maidens: Int?,
    @SerializedName("matches_played")
    val matchesPlayed: Int?,
    @SerializedName("overs")
    val overs: Double?,
    @SerializedName("runs_conceded")
    val runsConceded: Int?,
    @SerializedName("strike_rate")
    val strikeRate: Double?,
    @SerializedName("wickets")
    val wickets: Int?
): Parcelable