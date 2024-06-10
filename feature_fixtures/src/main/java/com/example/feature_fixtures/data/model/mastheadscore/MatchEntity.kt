package com.example.feature_fixtures.data.model.mastheadscore

import com.google.gson.annotations.SerializedName

data class MatchEntity(
    @SerializedName("championship_id")
    val championshipId: String?,
    @SerializedName("championship_name")
    val championshipName: String?,
    @SerializedName("comp_type_id")
    val compTypeId: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("event_coverage_level")
    val eventCoverageLevel: String?,
    @SerializedName("event_coverage_level_id")
    val eventCoverageLevelId: String?,
    @SerializedName("event_day")
    val eventDay: String?,
    @SerializedName("event_duration_left")
    val eventDurationLeft: String?,
    @SerializedName("event_format")
    val eventFormat: String?,
    @SerializedName("event_group")
    val eventGroup: String?,
    @SerializedName("event_is_daynight")
    val eventIsDaynight: String?,
    @SerializedName("event_islinkable")
    val eventIslinkable: String?,
    @SerializedName("event_livecoverage")
    val eventLivecoverage: String?,
    @SerializedName("event_name")
    val eventName: String?,
    @SerializedName("event_priority")
    val eventPriority: String?,
    @SerializedName("event_session")
    val eventSession: String?,
    @SerializedName("event_stage")
    val eventStage: String?,
    @SerializedName("event_state")
    var eventState: String?,
    @SerializedName("event_status")
    val eventStatus: String?,
    @SerializedName("event_status_id")
    val eventStatusId: String?,
    @SerializedName("event_sub_status")
    val eventSubStatus: String?,
    @SerializedName("game_id")
    val gameId: String?,
    @SerializedName("has_standings")
    val hasStandings: String?,
    @SerializedName("league_code")
    val leagueCode: String?,
    @SerializedName("league_id")
    val leagueId: String?,
    @SerializedName("parent_series_id")
    val parentSeriesId: String?,
    @SerializedName("parent_series_name")
    val parentSeriesName: String?,
    @SerializedName("participants")
    val participantEntities: List<ParticipantEntity>?,
    @SerializedName("result_code")
    val resultCode: String?,
    @SerializedName("result_sub_code")
    val resultSubCode: String?,
    @SerializedName("series_end_date")
    val seriesEndDate: String?,
    @SerializedName("series_id")
    val seriesId: String?,
    @SerializedName("series_name")
    val seriesName: String?,
    @SerializedName("series_start_date")
    val seriesStartDate: String?,
    @SerializedName("sport")
    val sport: String?,
    @SerializedName("start_date")
    var startDate: String?,
    @SerializedName("tour_id")
    val tourId: String?,
    @SerializedName("tour_name")
    val tourName: String?,
    @SerializedName("venue_gmt_offset")
    val venueGmtOffset: String?,
    @SerializedName("venue_id")
    val venueId: String?,
    @SerializedName("venue_name")
    val venueName: String?,
    @SerializedName("winning_margin")
    val winningMargin: String?
)