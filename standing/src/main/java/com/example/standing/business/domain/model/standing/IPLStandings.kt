package com.example.standing.business.domain.model.standing

data class IPLStandings(
    val isShowForm: Boolean = false,
    val teamID: Int,
    val teamIsQualified: Boolean,
    val teamPosition: String?,
    val title: String?,
    val teamLogoUrl: String? = null,
    var scoreList: List<String?> = emptyList(),
    val lastMatchesResult: List<String?> = emptyList()
)
