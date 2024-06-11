package com.example.demoprojectmultimodule


import com.example.base.helper.BaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseInfoImpl @Inject constructor(
) : BaseInfo {

    override fun getBaseUrl(): String {
        return "https://www.punjabkingsipl.in/"
    }

    override fun getApiAuthKey(): String {
        return ""
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

}