package com.example.demoprojectmultimodule.data.data.model

import com.google.gson.annotations.SerializedName

data class AppTypePath(
    @SerializedName("KKR")
    val kKR: KKR?,
    @SerializedName("LAKR")
    val lAKR: LAKR?,
    @SerializedName("TKR")
    val tKR: TKR?,
    @SerializedName("ADKR")
    val aDKR: ADKR?
)