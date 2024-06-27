package com.example.photo_listing.business.domain.model.userreaction

import com.google.gson.annotations.SerializedName

data class Timestamp(
    @SerializedName("istTime")
    val istTime: String?,
    @SerializedName("utcTime")
    val utcTime: String?
)