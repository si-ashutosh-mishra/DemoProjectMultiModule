package com.example.demoprojectmultimodule.data.data.model

import com.google.gson.annotations.SerializedName

data class TKR(
    @SerializedName("layout_builder_finder")
    val layoutBuilderFinder: String?,
    @SerializedName("news_page_listing_finder")
    val newsPageListingFinder: String?,
    @SerializedName("videos_page_listing_finder")
    val videosPageListingFinder: String?,
    @SerializedName("photos_page_listing_finder")
    val photosPageListingFinder: String?,
    @SerializedName("press_page_listing_finder")
    val pressPageListingFinder: String?,
    @SerializedName("fixture_method_type")
    val fixtureMethodType: String?,
    @SerializedName("fixture_client_id")
    val fixtureClientId: String?,
    @SerializedName("fixture_sport")
    val fixtureSport: String?,
    @SerializedName("fixture_league")
    val fixtureLeague: String?,
    @SerializedName("fixture_time_zone")
    val fixtureTimeZone: String?,
    @SerializedName("fixture_language")
    val fixtureLanguage: String?,
    @SerializedName("fixture_tournament_id")
    val fixtureTournamentId: String?,
    @SerializedName("tkr_team_id")
    val tkrTeamID: String?,
    @SerializedName("tkr_series_id")
    val tkrSeriesID: String?,
    @SerializedName("tkr_news_entities")
    val tkrNewsEntities: String?,
    @SerializedName("tkr_videos_entities")
    val tkrVideosEntities: String?,
    @SerializedName("tkr_photos_entities")
    val tkrPhotosEntities: String?,
    @SerializedName("tkr_team_image")
    val tkrTeamImage: String?,
    @SerializedName("tkr_player_image")
    val tkrPlayerImage: String?,
    @SerializedName("tkr_fan_poll_entity")
    val tkrFanPollEntity: String?,
    @SerializedName("mens_team")
    val mensTeam: TeamDetail?,
    @SerializedName("womens_team")
    val womensTeam: TeamDetail?,
    @SerializedName("tkr_nationality_id")
    val tkrNationalityId: String?,
    @SerializedName("ig_handle")
    val igHandle: String?,
    @SerializedName("team_type_schedulr_fixture")
    val teamTypeSchedulrFixture: String?

)