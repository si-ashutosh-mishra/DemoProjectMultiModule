package com.example.feature_news.business.domain.model.listing

import com.knightclub.app.business.domain.model.listing.AssetUtils
import com.knightclub.app.data.model.loyalty.BannerRedirectionModel
import java.io.Serializable

data class AssetItem(
//    val featured: Boolean = false,
    val assetId: Int?,
//    val duration: String,
    val beautifiedDuration: String?,
    val assetGuid: String?,
    val shortDesc: String?,
    val assetTitle: String?,
//    val shortTitle: String?,
    val titleAlias: String?,
//    val createdDate: String?,
//    val displayName: String?,
//    val totalAssets: String?,
    val assetTypeId: Int,
    val publishedDate: String?,
//    val primaryEntityRoleMapId: Int,
    val secondaryEntityRoleMapId: Int,
    val imageUrl: String?,
    val videoUrl: String?,
//    val displayPublishedDate: String?,
    var isLiked: Boolean = false,
    val sharingUrl: String?,
//    val contentSourceId: String?,
//    val videoUrl: String?,
    val totalAssets: String?,
    var totalReacts: String?,
    val description: String?,
    val offer: String?,
    val price: String?,
    val discountPrice: String?,
    val externalLink: String?,
    val infoUrl:String?,
    val contentSourceId: String? = null,
    val likeReacts:String? = null,
    val tag:String? = "",
    val redirectionPayload:BannerRedirectionModel? = null,
) : Serializable {
    val assetType: AssetUtils.AssetType =
        AssetUtils.getAssetType(assetTypeId, secondaryEntityRoleMapId)

    fun getIdWithAssetType():String{
        return assetId.toString() + "_" + assetType.typeId.toString()
    }
}