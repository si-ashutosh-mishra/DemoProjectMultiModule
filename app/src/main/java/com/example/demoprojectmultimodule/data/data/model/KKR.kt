package com.example.demoprojectmultimodule.data.data.model

import com.google.gson.annotations.SerializedName

data class KKR(
    @SerializedName("layout_builder_finder")
    val layoutBuilderFinder: String?,
    @SerializedName("news_page_listing_finder")
    val newsPageListingFinder: String?,
    @SerializedName("videos_page_listing_finder")
    val videosPageListingFinder: String?,
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
    @SerializedName("kkr_team_id")
    val kkrTeamID: String?,
    @SerializedName("kkr_series_id")
    val kkrSeriesID: String?,
    @SerializedName("kkr_news_entities")
    val kkrNewsEntities: String?,
    @SerializedName("kkr_videos_entities")
    val kkrVideosEntities: String?,
    @SerializedName("kkr_photos_entities")
    val kkrPhotosEntities: String?,
    @SerializedName("kkr_team_image")
    val kkrTeamImage: String?,
    @SerializedName("kkr_player_image")
    val kkrPlayerImage: String?,
    @SerializedName("kkr_fan_poll_entity")
    val kkrFanPollEntity: String?,
    @SerializedName("mens_team")
    val mensTeam: TeamDetail?,
    @SerializedName("kkr_nationality_id")
    val kkrNationalityId: String?,
    @SerializedName("ig_handle")
    val igHandle: String?,
    @SerializedName("team_type_schedulr_fixture")
    val teamTypeSchedulrFixture: String?,
    @SerializedName("sync_calendar_url")
    val syncCalendarUrl: String?,
    @SerializedName("fixtures_pdf_url")
    val fixturesPdfUrl: String?,
    @SerializedName("quiz_bg")
    val quizBg: String?,
    @SerializedName("player_profile_url")
    val playerProfileUrl: String?,
    @SerializedName("buy_ticket_url")
    val buyTicketUrl: String?,
    @SerializedName("fixtures_url")
    val fixturesUrl: String?,

    @SerializedName("reel_entities")
    val reelEntities: String?,
    @SerializedName("reel_exclude_entities")
    val reelExcludeEntities: String?,
    @SerializedName("reel_other_entities")
    val reelOtherEntities: String?

)