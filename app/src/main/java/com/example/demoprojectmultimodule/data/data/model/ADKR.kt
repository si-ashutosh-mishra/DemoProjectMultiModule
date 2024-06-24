package com.example.demoprojectmultimodule.data.data.model

import com.google.gson.annotations.SerializedName

data class ADKR(
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
    @SerializedName("adkr_team_id")
    val adkrTeamID: String?,
    @SerializedName("adkr_series_id")
    val adkrSeriesID: String?,
    @SerializedName("adkr_news_entities")
    val adkrNewsEntities: String?,
    @SerializedName("adkr_videos_entities")
    val adkrVideosEntities: String?,
    @SerializedName("adkr_photos_entities")
    val adkrPhotosEntities: String?,
    @SerializedName("adkr_team_image")
    val adkrTeamImage: String?,
    @SerializedName("adkr_player_image")
    val adkrPlayerImage: String?,
    @SerializedName("adkr_fan_poll_entity")
    val adkrFanPollEntity: String?,
    @SerializedName("mens_team")
    val mensTeam: TeamDetail?,
    @SerializedName("adkr_nationality_id")
    val adkrNationalityId: String?,
    @SerializedName("ig_handle")
    val igHandle: String?,
    @SerializedName("team_type_schedulr_fixture")
    val teamTypeSchedulrFixture: String?



)