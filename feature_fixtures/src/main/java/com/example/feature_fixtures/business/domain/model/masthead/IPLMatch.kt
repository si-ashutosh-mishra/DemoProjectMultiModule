package com.example.feature_fixtures.business.domain.model.masthead

import java.io.Serializable

data class IPLMatch(
    val endDate: String?,
    val eventDurationLeft: String?,
    val eventGroup: String?,
    val eventIsDaynight: String?,
    val eventIslinkable: String?,
    val eventLivecoverage: String?,
    val eventName: String?,
    val eventStage: String?,
    var eventState: EventState,
    val eventStateText: String,
    val eventStatus: String?,
    val eventStatusId: String?,
    val eventSubStatus: String?,
    val gameId: String?,
    val leagueCode: String?,
    val participants: List<Participant>?,
    val resultCode: String?,
    val resultSubCode: String?,
    val seriesId: String?,
    val seriesName: String?,
    val sport: String?,
    var startDate: String?,
    val tourId: String?,
    val tourName: String?,
    val venueGmtOffset: String?,
    val venueId: String?,
    val venueName: String?,
    val winningMargin: String?,
    val eventStatusText: String?,
    val matchCenterWebUrl: String?,
    val matchReportLink:String? = "www.google.com",
    val matchHighLights:String? = null
) : Serializable
