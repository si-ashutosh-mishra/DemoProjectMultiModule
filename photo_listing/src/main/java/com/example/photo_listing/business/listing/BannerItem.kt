package com.example.photo_listing.business.listing

import com.example.lb_content_listing.business.domain.model.AssetUtils

data class BannerItem(
    val assetId: Int?,
    val bannerLink: String? = "",
    val bannerImageUrl: String? = "",
    val title: String? = "",
    val titleAlias: String? = "",
    val beautifiedDuration: String? = "",
    val publishedDate: String? = "",
    val height: Int? = null,
    val width: Int? = null,
    val ratio: Double? = null,
    var reactCount: String? = "",
    var isLiked: Boolean? = false,
    val sharingUrl: String? = null,
    val assetType: AssetUtils.AssetType = AssetUtils.getAssetType(
        assetTypeId = -1
    ),
    val albumCount: Int? = 0,
    val tag:String?="",
    val secondaryEntityRoleMapId:Int? = -1
){
    fun getIdWithAssetType():String{
        return assetId.toString() +"_"+assetType.typeId.toString()
    }
}
