package com.example.photo_listing.data.model

import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    @SerializedName("data")
    val dataEntity: PhotoMetaDataEntity?,
)