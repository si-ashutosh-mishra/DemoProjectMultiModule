package com.example.content_listing.business.domain.model

import java.io.Serializable

data class AssetItem(
    val assetId: Int? = 0,
    val beautifiedDuration: String? = null,
    val duration: String,
    val assetGuid: String? = null,
    val assetTitle: String? = null,
    val titleAlias: String? = null,
    val assetTypeId: Int = 0,
    val publishedDate: String?,
    val secondaryEntityRoleMapId: Int = 0,
    val imageUrl: String? = null,
    val sharingUrl: String? = null,
    val totalAssets: String? = null,
    var totalReacts: String? = null,
    val description: String? = null,
    val offer: String? = null,
    val price: String? = null,
    val discountPrice: String? = null,
    val externalLink: String? = null,
    val contentSourceId: String? = null,
    val bannerLink: String? = null,
    val bannerImage: String? = null,
    val webViewUrl: String? = null,
    val isWebView: Boolean? = false,
    val inAppBrowser: Boolean? = false,
    val isExternalWebView: Boolean? = false,
    val isWebAuth: Boolean? = false,
    val primaryEntityDisplayName: String? = null,
    val primaryEntityRoleMapId: Int? = 0,
    val primaryName: String?,
    val secondaryEntityName: String?,
    val reelSharingUrl: String? = "",
    val videoUrl: String? = "",
    val secCanonicalUrl: String = "",
    var likesCounts: Int?,
    var isLikedByUser: Boolean = false
) : Serializable {
    private val assetType: AssetUtils.AssetType = AssetUtils.getAssetType(assetTypeId)

    fun getIdWithAssetType(): String {
        return assetId.toString() + "_" + assetType.typeId.toString()
    }
}