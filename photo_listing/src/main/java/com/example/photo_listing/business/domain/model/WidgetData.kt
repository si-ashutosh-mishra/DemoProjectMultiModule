package com.example.photo_listing.business.domain.model

import com.google.gson.annotations.SerializedName

data class WidgetData(
    @SerializedName("items")
    val items: List<AssetItemEntity>?,
    @SerializedName("data")
    val data: Data?
)