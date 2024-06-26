package com.example.feature_news.data.model.layoutbuilder

import com.example.content_listing.data.model.AssetItemEntity
import com.google.gson.annotations.SerializedName
import com.knightclub.app.data.model.layoutbuilder.Data

data class WidgetData(
    @SerializedName("items")
    val items: List<AssetItemEntity>?,
    @SerializedName("data")
    val data: Data?
)