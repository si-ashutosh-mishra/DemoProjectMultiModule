package com.example.base_layout_builder.data.model.layoutbuilder

import com.example.content_listing.data.model.layoutbuilder.AssetMeta
import com.example.content_listing.data.model.layoutbuilder.Author
import com.example.content_listing.data.model.layoutbuilder.EntityData
import com.google.gson.annotations.SerializedName

data class AssetMap(
    @SerializedName("asset_id")
    val assetId: Int?,
    @SerializedName("asset_meta")
    val assetMeta: AssetMeta?,
    @SerializedName("asset_order")
    val assetOrder: Int?,
    @SerializedName("asset_type")
    val assetType: Int?,
    @SerializedName("author")
    val author: List<Author>?,
    @SerializedName("entitydata")
    val entitydata: List<EntityData>?,
    @SerializedName("publish_date")
    val publishDate: String?,
    @SerializedName("published_version_number")
    val publishedVersionNumber: Int?,
    @SerializedName("album_count")
    val album_count: Int?
)