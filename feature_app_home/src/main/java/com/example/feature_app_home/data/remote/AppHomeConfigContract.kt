package com.example.feature_app_home.data.remote

interface AppHomeConfigContract {
    fun getAppHomeUrl(): String
    fun getAppHomeFixturesUrl(): String
    fun getCurrentTeamID():Int
    fun getHomeTeamCount():Int
    fun isSwap():Boolean
    fun swapPos():Int
    fun getFixturesPollingInterval(): Long
}