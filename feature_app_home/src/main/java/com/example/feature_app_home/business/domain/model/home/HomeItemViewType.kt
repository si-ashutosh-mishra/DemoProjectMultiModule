package com.example.feature_app_home.business.domain.model.home

enum class HomeItemViewType(val id: Int) {
    HOME_STANDING(1),
    HOME_FIXTURES(id = 2),
    HOME_SQUAD(id = 3),
    UNKNOWN(id = -1),
}