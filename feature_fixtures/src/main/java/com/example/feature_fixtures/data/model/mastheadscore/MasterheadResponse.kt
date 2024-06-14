package com.example.feature_fixtures.data.model.mastheadscore

import com.google.gson.annotations.SerializedName

data class MasterHeadResponse(
    @SerializedName("matches")
    val matches: List<MatchEntity>?,
    val teamId:String?,
    val itemCount:Int
)