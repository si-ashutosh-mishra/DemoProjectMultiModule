package com.example.photo_listing.business.domain.model.reaction

import com.google.gson.annotations.SerializedName

data class ReactionCountRequest(
    @SerializedName("assetId") val assetIds: List<String>,
    @SerializedName("subClientId") val subClientId:String
)