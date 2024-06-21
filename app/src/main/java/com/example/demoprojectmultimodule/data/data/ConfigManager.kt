package com.example.demoprojectmultimodule.data.data

import com.example.base.helper.BaseConfigContract
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.standing.data.remote.StandingConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigManager @Inject constructor(
) : BaseConfigContract, FixtureConfigContract, StandingConfigContract {

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
                "{team_id}",
                clubId
            )
    }

    override fun getFixturesUrl(): String {
        return "default.aspx?methodtype=3&client=7756e60237&sport=1&league=0&timezone=0530&language=0&tournament=4848"
    }

    override fun getStandingUrl(): String {
        return getBaseUrl() + "cricket/live/json/standing_5157.json"
    }

}