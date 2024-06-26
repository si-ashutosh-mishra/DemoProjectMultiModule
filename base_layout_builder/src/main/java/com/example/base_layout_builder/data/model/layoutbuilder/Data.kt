package com.example.base_layout_builder.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("guid")
    val guid: String,
    @SerializedName("asset_map")
    val assetMap: List<AssetMap>?,
)
