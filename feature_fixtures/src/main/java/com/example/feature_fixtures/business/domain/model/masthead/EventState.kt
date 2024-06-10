package com.example.feature_fixtures.business.domain.model.masthead

enum class EventState(val id: String) {
    RESULT("R"),
    LIVE("L"),
    UPCOMING("U")
}

fun String?.toMatchEventState(): EventState {
    return when (this) {
        EventState.RESULT.id -> EventState.RESULT
        EventState.LIVE.id -> EventState.LIVE
        else -> EventState.UPCOMING
    }
}