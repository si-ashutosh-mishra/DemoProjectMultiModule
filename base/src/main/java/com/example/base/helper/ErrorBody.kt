package com.example.base.helper

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)