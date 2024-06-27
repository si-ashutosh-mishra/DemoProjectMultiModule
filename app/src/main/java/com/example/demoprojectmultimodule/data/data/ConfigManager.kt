package com.example.demoprojectmultimodule.data.data

import com.example.base.helper.BaseConfigContract
import com.example.content_listing.data.remote.ContentListingConfigContract
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.feature_fixtures.data.remote.PhotoListingConfig
import com.example.standing.data.remote.StandingConfigContract
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigManager @Inject constructor(
) : BaseConfigContract, FixtureConfigContract, StandingConfigContract,
    ContentListingConfigContract,PhotoListingConfig {

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

    override fun getPhotosPageListingUrl(): String {
       /* val appTypePath = ""//getAppTypePath()
        val photosListingFinder = when (getAppType()) {
            AppType.LAKR.id -> appTypePath?.lAKR?.photosPageListingFinder
            AppType.TKR.id -> appTypePath?.tKR?.photosPageListingFinder
            AppType.ADKR.id -> appTypePath?.aDKR?.photosPageListingFinder
            else -> "app-photos"
        }
        return getBaseApiUrl() + firebaseRemoteConfig.getString(KEY_PHOTOS_BUILDER_URL)
            .replace(ReplaceKeys.PHOTOS_PAGE_LISTING_FINDER, photosListingFinder ?: "")*/
        return ""
    }

    override fun getReactionCountUrl(): String {
        return getBaseUrl() + ""
    }

    override fun getAppType(): Int {
       /* var result: Int
        runBlocking {
            result = sessionStoreManager.getTypeOfApp().firstOrNull() ?: AppType.getDefaultAppType()
        }
        return result*/
        return 0
    }

    override fun getUserReactionUrl(): String {
        return getBaseUrl() + ""//firebaseRemoteConfig.getString(KEY_FLRP_GET_USER_REACTION)
    }

}