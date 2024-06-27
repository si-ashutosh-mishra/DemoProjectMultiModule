package com.example.photo_listing.business.domain.model.reaction

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("asset")
        val asset: List<Asset?>?
    )