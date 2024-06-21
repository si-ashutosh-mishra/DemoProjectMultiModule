package com.example.demoprojectmultimodule


import com.example.base.helper.BaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseInfoImpl @Inject constructor(
) : BaseInfo {

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
        return getBaseUrl() + "static-assets/images/teams/{team_id}.png?v=1.12"
            .replace("{team_id}", clubId)
    }

}