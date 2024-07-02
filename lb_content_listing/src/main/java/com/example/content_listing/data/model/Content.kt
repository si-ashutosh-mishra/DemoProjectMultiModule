package com.example.content_listing.data.model

import com.example.lb_content_listing.data.model.layoutbuilder.AssetItemEntity
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("module")
    val module: List<Module>?,
    @SerializedName("items")
    val assetItemEntities: List<AssetItemEntity>
)