package com.example.lb_content_listing.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class ApiConfig(
    @SerializedName("feedpath")
    val feedPath: String?
)
