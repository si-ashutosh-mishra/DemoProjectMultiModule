package com.example.photo_listing.business.model

import java.io.Serializable

data class PhotoDetails(
    val title: String?,
    val guid: String?,
    val albumId: String?,
    val albumDesc: String?,
    val albumItems: List<PhotoItem>?,
    val publishedDate: String?,
    val categoryTag: String?,
    val sharingUrl: String?,
    val tagsId: String?,
    val tagsName: String?,
    val assetType: String?,
): Serializable


data class PhotoItem(
    val image_id: String?,
    val title: String?,
    val caption: String?,
    val desc: String?,
    val imageUrl: String?,
    val isCover: Boolean,
) : Serializable