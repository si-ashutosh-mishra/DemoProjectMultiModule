package com.example.standing.data.model.standings


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("name")
    val name: String?,
    @SerializedName("team")
    val team: List<Team>?
)