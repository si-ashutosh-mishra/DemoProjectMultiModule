package com.example.feature_squad.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OverAllStats(
    @SerializedName("batting")
    val batting: Batting?,
    @SerializedName("bowling")
    val bowling: Bowling?,
    @SerializedName("fielding")
    val fielding: Fielding?
): Parcelable