package com.example.photo_listing.business.domain.model.photolisting

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("items")
    val assetItemEntities: List<AssetItemEntity>,
    @SerializedName("module")
    val module: List<Module>?,
)
