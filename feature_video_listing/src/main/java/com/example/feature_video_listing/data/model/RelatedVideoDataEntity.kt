package com.example.feature_video_listing.data.model

import com.example.lb_content_listing.data.model.layoutbuilder.AssetItemEntity
import com.google.gson.annotations.SerializedName

data class RelatedVideoDataEntity(
    @SerializedName("items")
    val items: List<AssetItemEntity>
)