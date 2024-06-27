package com.example.photo_listing.business.domain.model.userreaction

import com.google.gson.annotations.SerializedName

data class AssetReaction(
    @SerializedName("assetId")
    val assetId: String?,
    @SerializedName("datetime")
    val datetime: String?,
    @SerializedName("reactionId")
    val reactionId: String?,
    @SerializedName("userName")
    val userName: String?,
    @SerializedName("userProfile")
    val userProfile: String?
)