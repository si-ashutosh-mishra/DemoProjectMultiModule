package com.example.content_listing.data.model

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("items")
    val assetItemEntities: List<AssetItemEntity>
)