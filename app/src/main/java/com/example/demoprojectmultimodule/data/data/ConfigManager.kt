package com.example.demoprojectmultimodule.data.data

import com.example.base.helper.BaseConfigContract
import com.example.lb_content_listing.data.remote.ContentListingConfigContract
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.photo_listing.data.remote.PhotoListingConfig
import com.example.standing.data.remote.StandingConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigManager @Inject constructor(
) : BaseConfigContract, FixtureConfigContract, StandingConfigContract,
    ContentListingConfigContract, PhotoListingConfig {

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
        return getBaseUrl() + getBaseContentImageUrl()
            .replace(
                /*ReplaceKeys.IMAGE_PATH*/"{image_path}",
                imageRatio?.let { imagePath?.replace("/0/", "/$imageRatio/") }
                    ?: imagePath.orEmpty())
            .replace(/*ReplaceKeys.IMAGE_NAME*/"{image_name}", imageName.orEmpty())
            .replace(
                /*ReplaceKeys.CONTENT_IMAGE_VERSION*/"{content_image_version}", /*firebaseRemoteConfig.getString(
            KEY_CONTENT_IMAGE_VERSION
        )*/"1.30"
            )
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

    override fun getCorousalImageUrl(
        imagePath: String?, imageName: String?, imageRatio: String?): String {
        return getBaseUrl() + getBaseContentImageUrl()
            .replace(
                /*ReplaceKeys.IMAGE_PATH*/"{image_path}",
                imageRatio?.let { imagePath?.replace("/0/", "/$imageRatio/") }
                    ?: imagePath.orEmpty())
            .replace(/*ReplaceKeys.IMAGE_NAME*/"{image_name}", imageName.orEmpty())
            .replace(
                /*ReplaceKeys.CONTENT_IMAGE_VERSION*/"{content_image_version}", /*firebaseRemoteConfig.getString(
            KEY_CONTENT_IMAGE_VERSION
        )*/"1.30"
            )
    }

    private fun getBaseContentImageUrl(): String {
        return "static-assets/waf-images/{image_path}{image_name}?v={content_image_version}"
        //return firebaseRemoteConfig.getString(KEY_BASE_CONTENT_IMAGE_PATH)
    }
}