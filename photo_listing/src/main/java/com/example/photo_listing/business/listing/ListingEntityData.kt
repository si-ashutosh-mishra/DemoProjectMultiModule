package com.example.photo_listing.business.listing

import android.os.Parcelable


data class ListingEntityData(
    val entities: String,
    val excludeEntities: String,
    val otherEntities: String,
    val isReelApi: Boolean = false,
    val extras: String = "",
)
