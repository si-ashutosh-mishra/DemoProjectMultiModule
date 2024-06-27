package com.example.lb_content_listing.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class WidgetData(
    @SerializedName("items")
    val items: List<AssetItemEntity>?,
    @SerializedName("data")
    val data: Data?
)