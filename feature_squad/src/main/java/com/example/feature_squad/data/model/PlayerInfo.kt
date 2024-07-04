package com.example.feature_squad.data.model

import com.google.gson.annotations.SerializedName

data class PlayerInfo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("skill")
    val skill: Skill?
)

data class Skill(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("short_name")
    val shortName: String?
)
