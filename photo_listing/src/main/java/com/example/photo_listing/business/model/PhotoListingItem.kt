package com.example.photo_listing.business.model

import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.business.listing.BannerItem
import com.example.photo_listing.business.listing.ListingEntityData
import com.example.photo_listing.presentation.PhotoItemViewType


sealed class PhotoListingItem(val type: PhotoItemViewType) {
    open class PhotosArticle(open val title: String, open val items: List<AssetItem>, open val entityData: ListingEntityData, type: PhotoItemViewType): PhotoListingItem(type = type)
    data class Carousel(val title: String, val items: List<BannerItem>): PhotoListingItem(PhotoItemViewType.CAROUSEL)
    data class Banner(val title:String,val bannerImage: String,val bannerLink:String): PhotoListingItem(type = PhotoItemViewType.BANNER)

    data class Training(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): PhotosArticle(title = title, items = items, entityData = entityData, type = PhotoItemViewType.TRAINING)
    data class BehindScene(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): PhotosArticle(title = title, items = items, entityData = entityData, type = PhotoItemViewType.BEHINDSCENES)
    data class MatchPhotos(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): PhotosArticle(title = title, items = items, entityData = entityData, type = PhotoItemViewType.MATCHPHOTOS)
    object Unknown: PhotoListingItem(type = PhotoItemViewType.UNKNOWN)
}
