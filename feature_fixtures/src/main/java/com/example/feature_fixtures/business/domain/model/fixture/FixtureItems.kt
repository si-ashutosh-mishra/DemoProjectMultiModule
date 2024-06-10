package com.example.feature_fixtures.business.domain.model.fixture

import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch


data class FixtureItems(
    val allListOfMatches:List<IPLMatch>?,
    val allLiveMatches:List<IPLMatch>?,
    val allUpcomingMatches:List<IPLMatch>?,
    val allRecentMatches:List<IPLMatch>?
)
