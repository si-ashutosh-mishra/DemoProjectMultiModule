package com.example.lb_content_listing.data.remote

interface ContentListingConfigContract {
    fun getContentImageUrl(
        imagePath: String?, imageName: String?, imageRatio: String? = null
    ): String

    fun getContentSharingUrl(entityCategory: String?, titleAlias: String?): String

    fun getReelsSharingUrl(
        entityCategory: String?, titleAlias: String?, assetId: Int? = 0, assetTypeId: Int? = 0
    ): String
}