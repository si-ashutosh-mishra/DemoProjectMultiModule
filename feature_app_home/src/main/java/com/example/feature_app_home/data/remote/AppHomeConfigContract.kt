package com.example.feature_app_home.data.remote

interface AppHomeConfigContract {
    fun getAppHomeUrl(): String
    fun getStandingUrl(): String
    fun getCurrentTeamID():Int
    fun getHomeTeamCount():Int
    fun isSwap():Boolean
    fun swapPos():Int
}