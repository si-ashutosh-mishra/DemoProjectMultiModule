package com.example.feature_squad.data.remote

interface SquadConfigContract {
    fun getSquadListingUrl(seriesId: String?, teamId: String?): String
    fun getPlayerImageUrl(playerId: String?): String
    fun getCountryNationalityIdImageUrl(nationalityId: String?): String
    fun getTeamNationalityId(currentTeam: Int): String?
    fun getSquadPlayerOrder(): List<String>
    fun getStaffImageUrl(staffId: String?): String
    fun getSquadStaffOrder(): List<String>
}