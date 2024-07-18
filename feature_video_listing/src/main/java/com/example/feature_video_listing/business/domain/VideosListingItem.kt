package com.example.feature_video_listing.business.domain

import com.example.feature_video_listing.presentation.videolist.VideosItemViewType
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.feature_video_listing.business.listing.BannerItem
import com.example.feature_video_listing.business.listing.ListingEntityData

sealed class VideosListingItem(val type: VideosItemViewType) {
    open class AssetsList(open val title: String, open val items: List<AssetItem>, open val entityData: ListingEntityData, type: VideosItemViewType): VideosListingItem(type = type)
    data class Carousel(val title: String, val items: List<BannerItem>, val entityData: ListingEntityData): VideosListingItem(type = VideosItemViewType.CAROUSEL)
    data class Banner(val title:String,val bannerImage: String,val bannerLink:String): VideosListingItem(type = VideosItemViewType.BANNER)
    data class BTS(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): AssetsList(title = title, items = items, entityData = entityData, type = VideosItemViewType.BTS)
    data class Films(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): AssetsList(title = title, items = items, entityData = entityData, type = VideosItemViewType.FILMS)
    data class Shorts(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): AssetsList(title = title, items = items, entityData = entityData, type = VideosItemViewType.SHORTS)
    data class Highlights(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): AssetsList(title = title, items = items, entityData = entityData, type = VideosItemViewType.HIGHLIGHTS)
    data class Suggestions(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): AssetsList(title = title, items = items, entityData = entityData, type = VideosItemViewType.SUGGESTIONS)
    object Unknown: VideosListingItem(type = VideosItemViewType.UNKNOWN)

}