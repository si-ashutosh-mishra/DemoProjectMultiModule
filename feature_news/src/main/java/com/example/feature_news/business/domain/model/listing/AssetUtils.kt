package com.knightclub.app.business.domain.model.listing

import com.knightclub.app.business.domain.model.sharing.SharingDomainData


object AssetUtils {

    fun getAssetType(assetTypeId: Int?, secondaryEntityRoleMapId: Int? = null): AssetType {
        return  when(assetTypeId) {
            AssetType.ARTICLE.typeId -> AssetType.ARTICLE
            AssetType.PHOTOS.typeId -> AssetType.PHOTOS
            AssetType.VIDEOS.typeId -> AssetType.VIDEOS
            AssetType.SHOP.typeId -> AssetType.SHOP
            AssetType.GAMING_HUB.typeId -> AssetType.GAMING_HUB
            else -> AssetType.UNKNOWN
        }

    }

    enum class AssetType(val typeId: Int, val secondaryEntityRoleMapId: Int? = null) {
        ARTICLE(1),
        PHOTOS(2),
        VIDEOS(4),
        SHOP(81),
        GAMING_HUB(101),
        UNKNOWN(-1)
    }

    fun getSharingBaseUrl(primaryRoleMapId: Int?, sharingDomainData: SharingDomainData?): String? {
        val sharingDomainItem = sharingDomainData?.sharingDomainItems?.firstOrNull { it?.id == primaryRoleMapId } ?: sharingDomainData?.sharingDomainItems?.firstOrNull { it?.id == 0 }
        return sharingDomainItem?.domainValue
    }
}