package com.example.base_layout_builder.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("module")
    val module: List<Module>?,
)