package com.example.content_listing.data.model.layoutbuilder

import com.example.content_listing.data.model.layoutbuilder.AssetItemEntity
import com.example.content_listing.data.model.layoutbuilder.Data
import com.google.gson.annotations.SerializedName

data class WidgetData(
    @SerializedName("items")
    val items: List<AssetItemEntity>?,
    @SerializedName("data")
    val data: Data?
)