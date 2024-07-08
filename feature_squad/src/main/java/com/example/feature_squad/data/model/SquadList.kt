package com.example.feature_squad.data.model

import com.google.gson.annotations.SerializedName

data class SquadList(
    @SerializedName("players")
    val players: List<Player>?,
    @SerializedName("support_staff")
    val supportStaff: List<Staff>?
)
