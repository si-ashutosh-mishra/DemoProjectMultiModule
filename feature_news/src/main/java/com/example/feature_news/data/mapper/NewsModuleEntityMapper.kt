package com.knightclub.app.business.mapper


import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.feature_news.presentation.news.typeone.NewsItemViewType
import com.knightclub.app.business.domain.model.Component
import com.knightclub.app.business.domain.model.WidgetView
import com.knightclub.app.business.domain.model.listing.AssetUtils
import com.knightclub.app.business.domain.model.listing.BannerItem
import com.knightclub.app.business.domain.model.news.NewsListingItem
import com.knightclub.app.data.model.layoutbuilder.Module
import javax.inject.Inject

class NewsModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper,
    private val listingEntityDataMapper: ListingEntityDataMapper,
    //private val configManager: ConfigManager
) :
    EntityMapper<List<Module>?, List<NewsListingItem>?> {

    override fun toDomain(entity: List<Module>?): List<NewsListingItem>? {

        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {

                NewsItemViewType.CAROUSEL -> {
                    if (module.widgetData?.data?.assetMap.isNullOrEmpty())
                        NewsListingItem.Unknown
                    else
                        NewsListingItem.Carousel(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.data?.assetMap?.map { assetMap ->
                                val entityDataPriority1 = assetMap.entitydata?.find { it.priority == 1 }
                                val entityDataPriority2 = assetMap.entitydata?.find { it.priority == 2 }
                                val categoryTag = entityDataPriority2?.entDispName ?: "Article"
                                val sharingUrl = ""/*configManager.getContentSharingUrl(
                                    baseUrl = AssetUtils.getSharingBaseUrl(primaryRoleMapId = entityDataPriority1?.entityRoleMapId, sharingDomainData = configManager.getContentSharingDetails()),
                                    entityCategory = entityDataPriority2?.canonical,
                                    titleAlias = assetMap.assetMeta?.titleAlias.orEmpty()
                                )*/
                                BannerItem(
                                    assetId = assetMap.assetId,
                                    title = assetMap.assetMeta?.title,
                                    bannerImageUrl = "https://www.knightclub.in/static-assets/waf-images/"
                                            +"${assetMap.assetMeta?.imagePath}"+"${assetMap.assetMeta?.imageName}"+"?v=1.30"
                                    /*configManager.getContentImageUrl(
                                        imagePath = assetMap.assetMeta?.imagePath ?: "",
                                        imageName = assetMap.assetMeta?.imageName ?: "",
                                        imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                    )*/,
                                    titleAlias = assetMap.assetMeta?.titleAlias,
                                    beautifiedDuration = CalendarUtils.getPublishedDuration(
                                        dateString = assetMap.publishDate,
                                        dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST
                                    ),
                                    publishedDate= CalendarUtils.convertDateStringToSpecifiedDateString(
                                        dateString = assetMap.publishDate,
                                        dateFormat = CalendarUtils.PUBLISHED_DATE_FORMAT,
                                        requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
                                    ),
                                    reactCount = null,
                                    assetType = AssetUtils.getAssetType(
                                        assetTypeId = assetMap.assetType,
                                        secondaryEntityRoleMapId = assetMap.entitydata?.find { entity -> entity.priority == 2 }?.entityRoleMapId
                                    ),
                                    sharingUrl = sharingUrl,
                                    tag = categoryTag
                                )
                            }.orEmpty()
                        )
                }

                NewsItemViewType.BANNER -> {
                    if (module.metaInfo == null)
                        NewsListingItem.Unknown
                    else
                        NewsListingItem.Banner(
                            title = module.displayTitle.orEmpty(),
                            bannerImage = "",//configManager.getBaseUrl() + module.metaInfo.bannerImage,
                            bannerLink = module.metaInfo.bannerLink ?: "",
                        )
                }

                NewsItemViewType.LATEST -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        NewsListingItem.Unknown
                    else
                        NewsListingItem.Latest(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = ""//ImageRatioMapper._6_4.value
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                NewsItemViewType.FEATURED -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        NewsListingItem.Unknown
                    else
                        NewsListingItem.Featured(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = ""//ImageRatioMapper._6_4.value
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                else -> NewsListingItem.Unknown
            }

        }.orEmpty()

    }

    private fun getWidgetType(componentName: String?, layoutType: String?): NewsItemViewType {
        return when {
            componentName == Component.SI_SHOWCASE.componentName && layoutType == WidgetView.LAYOUT_01 -> NewsItemViewType.CAROUSEL
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_02 -> NewsItemViewType.FEATURED
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> NewsItemViewType.LATEST
            componentName == Component.SI_ADS.componentName && layoutType == WidgetView.LAYOUT_04 -> NewsItemViewType.BANNER
            else -> NewsItemViewType.UNKNOWN
        }
    }

}