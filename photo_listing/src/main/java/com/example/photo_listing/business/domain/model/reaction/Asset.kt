package com.example.photo_listing.business.domain.model.reaction

import com.google.gson.annotations.SerializedName

data class Asset(
        @SerializedName("assetId")
        val assetId: String?,
        @SerializedName("totalCount")
        val totalCount: String?,
        @SerializedName("reaction")
        val reaction: List<Reaction>?
    )