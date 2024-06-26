package com.knightclub.app.business.domain.model.listing

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
        assetTypeId = -1,
        secondaryEntityRoleMapId = -1
    ),
    val albumCount: Int? = 0,
    val tag:String?="",
    val secondaryEntityRoleMapId:Int? = -1
){
    fun getIdWithAssetType():String{
        return assetId.toString() +"_"+assetType.typeId.toString()
    }
}
