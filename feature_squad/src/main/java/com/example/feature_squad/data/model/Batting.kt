package com.example.feature_squad.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Batting(
    @SerializedName("average")
    val average: Double?,
    @SerializedName("balls")
    val balls: Int?,
    @SerializedName("boundary_frequency")
    val boundaryFrequency: Double?,
    @SerializedName("boundary_percentage")
    val boundaryPercentage: Double?,
    @SerializedName("dot_ball_frequency")
    val dotBallFrequency: Double?,
    @SerializedName("dot_ball_percentage")
    val dotBallPercentage: Double?,
    @SerializedName("fifties")
    val fifties: Int?,
    @SerializedName("fours")
    val fours: Int?,
    @SerializedName("highest_score")
    val highestScore: Int?,
    @SerializedName("highest_score_notout")
    val highestScoreNotout: String?,
    @SerializedName("hundred")
    val hundred: Int?,
    @SerializedName("innings")
    val innings: Int?,
    @SerializedName("matches_played")
    val matchesPlayed: Int?,
    @SerializedName("not_outs")
    val notOuts: Int?,
    @SerializedName("runs")
    val runs: Int?,
    @SerializedName("sixes")
    val sixes: Int?,
    @SerializedName("strike_rate")
    val strikeRate: Double?
):Parcelable