package com.example.feature_app_home.business.domain.model.home

import com.example.standing.business.domain.model.standing.IPLStandings


sealed class HomeListingItem(val type: HomeItemViewType, open val dataAvailable: Boolean = true) {

    data class HomeStandingData(
        val widgetTitle: String,
        val showWidgetTitle: Boolean,
        val showMore: Boolean,
        val items: List<IPLStandings?>,
        override val dataAvailable: Boolean = false,
    ) : HomeListingItem(type = HomeItemViewType.HOME_STANDING)

    object Unknown : HomeListingItem(type = HomeItemViewType.UNKNOWN)
}