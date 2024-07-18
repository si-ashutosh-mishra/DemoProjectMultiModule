package com.example.photo_listing.data.model

import com.google.gson.annotations.SerializedName

data class AssetContent<T>(
    @SerializedName("data")
    val data: T,
)