package com.example.demoprojectmultimodule.data.data.model

import com.google.gson.annotations.SerializedName

data class LAKR(
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
    @SerializedName("lakr_team_id")
    val lakrTeamID: String?,
    @SerializedName("lakr_series_id")
    val lakrSeriesID: String?,
    @SerializedName("lakr_news_entities")
    val lakrNewsEntities: String?,
    @SerializedName("lakr_videos_entities")
    val lakrVideosEntities: String?,
    @SerializedName("lakr_photos_entities")
    val lakrPhotosEntities: String?,
    @SerializedName("lakr_team_image")
    val lakrTeamImage: String?,
    @SerializedName("lakr_player_image")
    val lakrPlayerImage: String?,
    @SerializedName("lakr_fan_poll_entity")
    val lakrFanPollEntity: String?,
    @SerializedName("mens_team")
    val mensTeam: TeamDetail?,
    @SerializedName("lakr_nationality_id")
    val lakrNationalityId: String?,
    @SerializedName("ig_handle")
    val igHandle: String?,
    @SerializedName("team_type_schedulr_fixture")
    val teamTypeSchedulrFixture: String?


)