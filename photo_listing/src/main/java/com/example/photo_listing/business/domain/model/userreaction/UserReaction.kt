package com.example.photo_listing.business.domain.model.userreaction

import com.google.gson.annotations.SerializedName

data class UserReaction(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("meta")
    val meta: Meta?
)