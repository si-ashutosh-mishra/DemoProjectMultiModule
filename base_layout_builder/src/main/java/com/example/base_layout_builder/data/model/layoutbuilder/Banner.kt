package com.example.base_layout_builder.data.model.layoutbuilder


import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("banner_image")
    val bannerImage: String?,
    @SerializedName("banner_link")
    val bannerLink: String?
)