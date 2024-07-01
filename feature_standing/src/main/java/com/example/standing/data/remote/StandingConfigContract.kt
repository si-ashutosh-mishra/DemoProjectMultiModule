package com.example.standing.data.remote

interface StandingConfigContract {
    fun getStandingUrl():String
    fun getTeamLogo(
        clubId: String
    ): String
    fun getStandingTitleList():List<String>
    fun getCurrentTeamID():Int
}