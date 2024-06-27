package com.example.photo_listing.business.domain.model.photolisting

import com.google.gson.annotations.SerializedName

data class ApiConfig(
    @SerializedName("feedpath")
    val feedPath: String?
)