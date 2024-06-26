package com.example.feature_news.business.domain.model.listing

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListingEntityData(
    val entities: String,
    val excludeEntities: String,
    val otherEntities: String,
    val isReelApi: Boolean = false,
    val extras: String = "",
): Parcelable
