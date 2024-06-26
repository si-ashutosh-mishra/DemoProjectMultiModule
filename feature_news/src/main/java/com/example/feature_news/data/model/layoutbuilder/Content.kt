package com.example.feature_news.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName
import com.knightclub.app.data.model.layoutbuilder.Module

data class Content(
    @SerializedName("items")
    val assetItemEntities: List<AssetItemEntity>,
    @SerializedName("module")
    val module: List<Module>?,
)