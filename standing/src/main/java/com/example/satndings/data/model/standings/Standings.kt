package com.example.satndings.data.model.standings


import com.google.gson.annotations.SerializedName

data class Standings(
    @SerializedName("stage1") val stage1: Stage?,
)

data class Stage(
    @SerializedName("groups") val groups: List<Group>?
)