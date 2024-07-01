package com.example.demoprojectmultimodule.data.data

import com.example.base.helper.BaseConfigContract
import com.example.feature_app_home.data.remote.AppHomeConfigContract
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.lb_content_listing.data.remote.ContentListingConfigContract
import com.example.standing.data.remote.StandingConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigManager @Inject constructor(
) : BaseConfigContract, FixtureConfigContract, StandingConfigContract, ContentListingConfigContract,
    AppHomeConfigContract {

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
        imagePath: String?, imageName: String?, imageRatio: String?,
    ): String {
        return getBaseUrl() + getBaseContentImageUrl().replace("{image_path}",
            imageRatio?.let { imagePath?.replace("/0/", "/$imageRatio/") } ?: imagePath.orEmpty())
            .replace(
                "{image_name}", imageName.orEmpty()
            )
    }

    override fun getContentSharingUrl(entityCategory: String?, titleAlias: String?): String {
        return ""
    }

    override fun getReelsSharingUrl(
        entityCategory: String?, titleAlias: String?, assetId: Int?, assetTypeId: Int?,
    ): String {
        return ""
    }

    override fun getStandingTitleList(): List<String> {
        return listOf("MP", "W", "L", "NRR", "PTS")
    }

    private fun getBaseContentImageUrl() =
        "static-assets/waf-images/{image_path}{image_name}?v=1.30"

    override fun getAppHomeUrl(): String {
        return "https://www.knightclub.in/apiv3/gettemplatedata?url=kkr-app-home&is_app=1"
    }
    override fun getCurrentTeamID() = 1106
    override fun getHomeTeamCount() = 5
    override fun swapPos() = 4
    override fun isSwap() = true


}