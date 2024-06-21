package com.example.satndings.data.model.standings


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("date")
    val date: String?,
    @SerializedName("filename")
    val filename: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("outcome")
    val outcome: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("score")
    val score: List<Score?>?,
    @SerializedName("super_over_loser_id")
    val superOverLoserId: String?,
    @SerializedName("super_over_winner_id")
    val superOverWinnerId: String?,
    @SerializedName("teama_id")
    val teamaId: String?,
    @SerializedName("teama_overs_played")
    val teamaOversPlayed: String?,
    @SerializedName("teama_score")
    val teamaScore: String?,
    @SerializedName("teama_wickets")
    val teamaWickets: String?,
    @SerializedName("teamb_id")
    val teambId: String?,
    @SerializedName("teamb_overs_played")
    val teambOversPlayed: String?,
    @SerializedName("teamb_score")
    val teambScore: String?,
    @SerializedName("teamb_wickets")
    val teambWickets: String?,
    @SerializedName("venue_fullname")
    val venueFullname: String?,
    @SerializedName("venue_id")
    val venueId: String?,
    @SerializedName("venue_shortname")
    val venueShortname: String?,
    @SerializedName("vs_fullname")
    val vsFullname: String?,
    @SerializedName("vs_id")
    val vsId: String?,
    @SerializedName("vs_shortname")
    val vsShortname: String?,
    @SerializedName("winner_id")
    val winnerId: String?
)