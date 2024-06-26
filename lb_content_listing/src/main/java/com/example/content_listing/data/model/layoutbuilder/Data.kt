package com.example.content_listing.data.model.layoutbuilder

import com.example.base_layout_builder.data.model.layoutbuilder.AssetMap
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("guid")
    val guid: String,
    @SerializedName("asset_map")
    val assetMap: List<AssetMap>?,
)
