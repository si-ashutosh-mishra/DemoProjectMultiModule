package com.example.base_layout_builder.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class ApiConfig(
    @SerializedName("feedpath")
    val feedPath: String?
)
