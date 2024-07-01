package com.example.base_layout_builder.data.model.layoutbuilder

import com.example.base_layout_builder.data.model.layoutbuilder.Data
import com.google.gson.annotations.SerializedName

data class WidgetData(
    @SerializedName("items")
    val items: List<AssetItemEntity>?,
    @SerializedName("data")
    val data: Data?
)