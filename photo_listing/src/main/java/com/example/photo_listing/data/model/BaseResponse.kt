package com.example.photo_listing.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("content")
    val content: T?,
    @SerializedName("status")
    val status: Int?
)