package com.example.photo_listing.business.domain.model.reaction

import com.google.gson.annotations.SerializedName

data class Reaction(
        @SerializedName("count")
        val count: Int?,
        @SerializedName("reactionId")
        val reactionId: String?
    )