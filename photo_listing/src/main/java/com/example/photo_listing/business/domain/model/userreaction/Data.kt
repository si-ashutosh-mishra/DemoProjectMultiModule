package com.example.photo_listing.business.domain.model.userreaction

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("assetReaction")
    val assetReaction: List<AssetReaction>?
)