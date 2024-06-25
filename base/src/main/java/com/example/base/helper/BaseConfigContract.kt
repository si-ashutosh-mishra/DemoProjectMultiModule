package com.example.base.helper



interface BaseConfigContract {
    fun getBaseUrl(): String
    fun getApiAuthKey(): String
    fun getPreferenceDataStoreName(): String
    fun getCaptchaSiteKey(): String
    fun getIsDebugMode(): Boolean
    fun getAppType(): String


}