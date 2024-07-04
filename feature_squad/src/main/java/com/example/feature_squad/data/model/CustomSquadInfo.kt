package com.example.feature_squad.data.model

import com.google.gson.annotations.SerializedName

data class CustomSquadInfo(
    @SerializedName("players")
    val players: List<PlayerInfo>?
)
