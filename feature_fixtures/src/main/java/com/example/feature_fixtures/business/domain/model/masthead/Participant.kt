package com.example.feature_fixtures.business.domain.model.masthead

import java.io.Serializable

data class Participant(
    val id: String?,
    val name: String?,
    val shortName: String?,
    var value: String?,
    val now: String?,
    val firstUp: String?,
    val teamImageUrl: String?,
    val highlight: Boolean?,
    val teamBackground:String?
): Serializable