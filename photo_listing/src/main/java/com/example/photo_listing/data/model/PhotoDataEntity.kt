package com.example.photo_listing.data.model

import com.google.gson.annotations.SerializedName
import com.knightclub.app.data.model.details.article.EntityData

data class PhotoDataEntity(
    @SerializedName("album_desc")
    val albumDesc: String?,
    @SerializedName("album_id")
    val albumId: String?,
    @SerializedName("images")
    val photos: List<PhotoEntity>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("entitydata")
    val entityData: List<EntityData>?,
    @SerializedName("title_alias")
    val titleAlias: String?,
    @SerializedName("slug_url")
    val slugUrl: String?,
    @SerializedName("asset_type")
    val assetType: String?,
)