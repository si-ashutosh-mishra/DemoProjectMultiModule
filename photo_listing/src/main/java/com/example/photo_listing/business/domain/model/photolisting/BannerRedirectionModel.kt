package com.example.photo_listing.business.domain.model.photolisting

import com.google.gson.annotations.SerializedName

data class BannerRedirectionModel(
    @SerializedName("analytics_title")
    val analyticsTitle: String?,
    @SerializedName("banner_image")
    val bannerImage: String?,
    @SerializedName("banner_type")
    val bannerType: String?,
    @SerializedName("display_title")
    val displayTitle: String?,
    @SerializedName("exclude_entities")
    val excludeEntities: List<Int?>?,
    @SerializedName("in_app_browser")
    val inAppBrowser: Boolean?,
    @SerializedName("is_external_webview")
    val isExternalWebview: Boolean?,
    @SerializedName("is_webview")
    val isWebview: Boolean?,
    @SerializedName("other_entities")
    val otherEntities: List<Any?>?,
    @SerializedName("required_entities")
    val requiredEntities: List<Int?>?,
    @SerializedName("title_alias")
    val titleAlias: String?,
    @SerializedName("webview_url")
    val webviewUrl: String?,
    @SerializedName("info_url")
    val infoUrl: String?,
    @SerializedName("login_required")
    val loginRequired: Boolean?,
    @SerializedName("is_gaming")
    val isGaming: Boolean?
)