package com.example.feature_squad.data.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("over_all_stats")
    val overAllStats: OverAllStats?,
    @SerializedName("player_details")
    val playerDetails: PlayerDetails?
)