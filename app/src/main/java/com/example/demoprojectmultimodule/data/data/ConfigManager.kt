package com.example.demoprojectmultimodule.data.data

import com.example.base.helper.BaseConfigContract
import com.example.demoprojectmultimodule.data.data.model.AppTypePath
import com.example.demoprojectmultimodule.util.AppType
import com.example.content_listing.data.remote.ContentListingConfigContract
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.feature_squad.data.remote.SquadConfigContract
import com.example.standing.data.remote.StandingConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigManager @Inject constructor(
) : BaseConfigContract, FixtureConfigContract, StandingConfigContract,
    ContentListingConfigContract, SquadConfigContract {

    override fun getBaseUrl(): String {
        return "https://www.knightclub.in/"
    }

    override fun getApiAuthKey(): String {
        return "eGxzdRsmKP"
    }

    override fun getPreferenceDataStoreName(): String {
        return ""
    }

    override fun getCaptchaSiteKey(): String {
        return ""
    }

    override fun getIsDebugMode(): Boolean {
        return true
    }

    override fun getAppType(): String {
        return ""
    }

    override fun getTeamLogo(clubId: String): String {
        return getBaseUrl() + "static-assets/images/teams/{team_id}.png?v=1.12".replace(
            "{team_id}", clubId
        )
    }

    override fun getTimeInterval(): Long {
        return 10000
    }

    override fun getFixturesUrl(): String {
        return "default.aspx?methodtype=3&client=7756e60237&sport=1&league=0&timezone=0530&language=0&tournament=4848"
    }

    override fun getStandingUrl(): String {
        return getBaseUrl() + "cricket/live/json/standing_5157.json"
    }

    override fun getContentImageUrl(
        imagePath: String?, imageName: String?, imageRatio: String?
    ): String {
        return ""
    }

    override fun getContentSharingUrl(entityCategory: String?, titleAlias: String?): String {
        return ""
    }

    override fun getReelsSharingUrl(
        entityCategory: String?, titleAlias: String?, assetId: Int?, assetTypeId: Int?
    ): String {
        return ""
    }

    override fun getSquadListingUrl(seriesId: String?, teamId: String?): String {
        /*return getBaseUrl() + firebaseRemoteConfig.getString(KEY_SQUAD_FEED_URL)
            .replace(
                ReplaceKeys.DEFAULT_SERIES_ID, seriesId ?: getDefaultSeriesId()
            )
            .replace(
                ReplaceKeys.DEFAULT_TEAM_ID, teamId ?: getDefaultTeamId()
            )*/
        return "https://stg-kc.sportz.io/cricket/static/json/iplfeeds/{team_id}_all_players_{series_id}.json".replace(
            ReplaceKeys.DEFAULT_SERIES_ID, seriesId ?: "5553"
        )
            .replace(
                ReplaceKeys.DEFAULT_TEAM_ID, teamId ?: "3840"
            )
    }

    override fun getPlayerImageUrl(playerId: String?): String {
        /*val appTypePath = getAppTypePath()
        val playerImagePathFinder = when (getAppType()) {
            AppType.KKR.id -> appTypePath?.kKR?.kkrPlayerImage
            AppType.LAKR.id -> appTypePath?.lAKR?.lakrPlayerImage
            AppType.TKR.id -> appTypePath?.tKR?.tkrPlayerImage
            AppType.ADKR.id -> appTypePath?.aDKR?.adkrPlayerImage
            else -> ""
        }
        return getBaseUrl() + firebaseRemoteConfig.getString(KEY_BASE_PLAYER_IMAGE_PATH)
            .replace(ReplaceKeys.PLAYER_ID, playerId.orEmpty())
            .replace(
                ReplaceKeys.DATA_IMAGE_VERSION, firebaseRemoteConfig.getString(
                    KEY_DATA_IMAGE_VERSION
                )
            ).replace(ReplaceKeys.PLAYER_IMAGE_PATH_FINDER, playerImagePathFinder ?: "")*/
        return "https://stg-kc.sportz.io/static-assets/images/players/{player_image_path_finder}/" +
                "{player_id}.png?v={data_image_version}".replace(ReplaceKeys.PLAYER_ID, playerId.orEmpty())
                    .replace(
                        ReplaceKeys.DATA_IMAGE_VERSION,""
                    ).replace(ReplaceKeys.PLAYER_IMAGE_PATH_FINDER, "")
    }

    override fun getCountryNationalityIdImageUrl(nationalityId: String?): String {
        /*return getBaseUrl() + firebaseRemoteConfig.getString(KEY_BASE_COUNTRY_CODE_IMAGE_PATH)
            .replace(ReplaceKeys.NATIONALITY_ID, nationalityId.orEmpty())
            .replace(
                ReplaceKeys.DATA_IMAGE_VERSION, firebaseRemoteConfig.getString(
                    KEY_DATA_IMAGE_VERSION
                )
            )*/
        return "https://stg-kc.sportz.io/static-assets/images/countries/" +
                "{nationality_id}.png?v={data_image_version}".
                replace(ReplaceKeys.NATIONALITY_ID, nationalityId.orEmpty())
                    .replace(
                        ReplaceKeys.DATA_IMAGE_VERSION, ""
                    )
    }

    override fun getTeamNationalityId(currentTeam: Int): String? {
        val appTypePath = getAppTypePath()

        return when (currentTeam ?: getAppType()) {
            AppType.KKR.id -> appTypePath?.kKR?.kkrNationalityId
            AppType.LAKR.id -> appTypePath?.lAKR?.lakrNationalityId
            AppType.TKR.id -> appTypePath?.tKR?.tkrNationalityId
            AppType.ADKR.id -> appTypePath?.aDKR?.adkrNationalityId
            else -> ""
        }
        return ""
    }

    private fun getAppTypePath(): AppTypePath? {
        return null
    }

    override fun getSquadPlayerOrder(): List<String> {
        /*return firebaseRemoteConfig.getString(KEY_SQUAD_PLAYER_ORDER).let {
            if (it.isBlank())
                emptyList()
            else
                it.split(",")
        }*/
        return emptyList()
    }

    override fun getStaffImageUrl(staffId: String?): String {
        /*return getBaseUrl() + firebaseRemoteConfig.getString(KEY_BASE_STAFF_IMAGE_PATH)
            .replace(ReplaceKeys.STAFF_ID, staffId.orEmpty())
            .replace(
                ReplaceKeys.DATA_IMAGE_VERSION, firebaseRemoteConfig.getString(
                    KEY_DATA_IMAGE_VERSION
                )
            )*/
        return ""
    }

    override fun getSquadStaffOrder(): List<String> {
        /*return firebaseRemoteConfig.getString(KEY_SQUAD_STAFF_ORDER).let {
            if (it.isBlank())
                emptyList()
            else
                it.split(",")
        }*/
        return emptyList()
    }
}


object ReplaceKeys {

    const val NATIONALITY_ID: String = "{nationality_id}"
    const val DATA_IMAGE_VERSION: String = "{data_image_version}"
    const val PLAYER_ID = "{player_id}"
    const val PLAYER_IMAGE_PATH_FINDER = "{player_image_path_finder}"
    const val DEFAULT_SERIES_ID = "{series_id}"
    const val DEFAULT_TEAM_ID = "{team_id}"

}