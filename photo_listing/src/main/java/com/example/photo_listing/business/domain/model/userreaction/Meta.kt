package com.example.photo_listing.business.domain.model.userreaction

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("message")
    val message: String?,
    @SerializedName("retVal")
    val retVal: Int?,
    @SerializedName("statusCode")
    val statusCode: Int?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("timestamp")
    val timestamp: Timestamp?
)