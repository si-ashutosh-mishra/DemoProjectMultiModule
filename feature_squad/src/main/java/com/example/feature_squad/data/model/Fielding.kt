package com.example.feature_squad.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fielding(
    @SerializedName("catches")
    val catches: Int?,
    @SerializedName("runouts")
    val runouts: Int?,
    @SerializedName("stumpings")
    val stumpings: Int?
): Parcelable