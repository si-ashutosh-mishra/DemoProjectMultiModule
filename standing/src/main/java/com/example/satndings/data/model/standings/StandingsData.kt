package com.example.satndings.data.model.standings


import com.google.gson.annotations.SerializedName

data class  StandingsData(
    @SerializedName("standings")
    val standings: Standings?
)