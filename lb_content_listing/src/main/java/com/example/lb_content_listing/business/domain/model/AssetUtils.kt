package com.example.lb_content_listing.business.domain.model


object AssetUtils {

    fun getAssetType(assetTypeId: Int?): AssetType {
        return  when(assetTypeId) {
            AssetType.ARTICLE.typeId -> AssetType.ARTICLE
            AssetType.PHOTOS.typeId -> AssetType.PHOTOS
            AssetType.VIDEOS.typeId -> AssetType.VIDEOS
            AssetType.SHOP.typeId -> AssetType.SHOP
            AssetType.GAMING_HUB.typeId -> AssetType.GAMING_HUB
            AssetType.KINGS_CUTS.typeId -> AssetType.KINGS_CUTS


            else -> AssetType.UNKNOWN
        }

    }

    enum class AssetType(val typeId: Int) {
        ARTICLE(1),
        PHOTOS(2),
        VIDEOS(4),
        SHOP(81),
        GAMING_HUB(101),
        UNKNOWN(-1),
        KINGS_CUTS(3)
    }

}