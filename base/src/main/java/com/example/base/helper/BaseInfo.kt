package com.example.base.helper



interface BaseInfo {
    fun getBaseUrl(): String
    fun getApiAuthKey(): String
    fun getPreferenceDataStoreName(): String
    fun getCaptchaSiteKey(): String
    fun getIsDebugMode(): Boolean

}