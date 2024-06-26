package com.example.feature_fixtures.data.remote

interface FixtureConfigContract {
    fun getFixturesUrl():String
    fun getTeamLogo(
        clubId: String
    ): String

    fun getTimeInterval():Long
}