package com.example.photo_listing.business.domain.model.photolisting

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("banner_image")
    val bannerImage: String?,
    @SerializedName("banner_link")
    val bannerLink: String?
)