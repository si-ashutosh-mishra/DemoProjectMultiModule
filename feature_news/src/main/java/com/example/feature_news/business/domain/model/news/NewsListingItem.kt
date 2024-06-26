package com.knightclub.app.business.domain.model.news

import com.example.feature_news.business.domain.model.listing.AssetItem
import com.example.feature_news.business.domain.model.listing.ListingEntityData
import com.example.feature_news.presentation.news.typeone.NewsItemViewType
import com.knightclub.app.business.domain.model.listing.BannerItem


sealed class NewsListingItem(val type: NewsItemViewType) {
    open class Articles(open val title: String, open val items: List<AssetItem>, open val entityData: ListingEntityData, type: NewsItemViewType): NewsListingItem(type = type)
    data class Carousel(val title: String, val items: List<BannerItem>): NewsListingItem(NewsItemViewType.CAROUSEL)

    data class Banner(val title:String,val bannerImage: String,val bannerLink:String): NewsListingItem(type = NewsItemViewType.BANNER)

    data class Latest(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): Articles(title = title, items = items, entityData = entityData, type = NewsItemViewType.LATEST)
    data class Featured(override val title: String, override val items: List<AssetItem>, override val entityData: ListingEntityData): Articles(title = title, items = items, entityData = entityData, type = NewsItemViewType.FEATURED)
    object Unknown: NewsListingItem(type = NewsItemViewType.UNKNOWN)
}
