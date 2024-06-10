package com.example.feature_fixtures.data.model.mastheadscore

import com.google.gson.annotations.SerializedName

data class ParticipantEntity(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("value")
    var value: String?,
    @SerializedName("now")
    val now: String?,
    @SerializedName("firstup")
    val firstUp: String?,
    @SerializedName("highlight")
    var highlight: Boolean?,
)