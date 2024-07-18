package com.example.feature_video_listing.business.mapper

import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.feature_video_listing.business.domain.Component
import com.example.feature_video_listing.business.domain.VideosListingItem
import com.example.feature_video_listing.business.domain.WidgetView
import com.example.feature_video_listing.presentation.videolist.VideosItemViewType
import com.example.lb_content_listing.business.domain.model.AssetUtils
import com.example.lb_content_listing.data.mapper.AssetItemEntityMapper
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import com.example.feature_video_listing.business.listing.BannerItem
import javax.inject.Inject

class VideoModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper,
    private val listingEntityDataMapper: ListingEntityDataMapper,
    //private val configManager: ConfigManager
) :
    EntityMapper<List<Module>?, List<VideosListingItem>?> {

    override fun toDomain(entity: List<Module>?): List<VideosListingItem>? {
        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {

                VideosItemViewType.CAROUSEL -> {
                    if (module.widgetData?.data?.assetMap.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.Carousel(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.data?.assetMap?.map { assetMap ->

                                val entityDataPriority1 = assetMap.entitydata?.find { it.priority == 1 }
                                val entityDataPriority2 = assetMap.entitydata?.find { it.priority == 2 }
                                val categoryTag = entityDataPriority2?.entDispName ?: "Video"
                                val sharingUrl = ""/*configManager.getContentSharingUrl(
                                    baseUrl = AssetUtils.getSharingBaseUrl(primaryRoleMapId = entityDataPriority1?.entityRoleMapId, sharingDomainData = configManager.getContentSharingDetails()),
                                    entityCategory = entityDataPriority2?.canonical,
                                    titleAlias = assetMap.assetMeta?.titleAlias.orEmpty()
                                )*/

                                BannerItem(
                                    assetId = assetMap.assetId,
                                    title = assetMap.assetMeta?.title,
                                    bannerImageUrl = "",/*configManager.getContentImageUrl(
                                        imagePath = assetMap.assetMeta?.imagePath ?: "",
                                        imageName = assetMap.assetMeta?.imageName ?: ""
                                    ),*/
                                    titleAlias = assetMap.assetMeta?.titleAlias,
                                    beautifiedDuration = CalendarUtils.getPublishedDuration(
                                        dateString = assetMap.publishDate,
                                        dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST
                                    ),
                                    publishedDate = assetMap.publishDate,
                                    reactCount = null,
                                    assetType = AssetUtils.getAssetType(
                                        assetTypeId = assetMap.assetType,
                                        secondaryEntityRoleMapId = assetMap.entitydata?.find { entity -> entity.priority == 2 }?.entityRoleMapId
                                    ),
                                    sharingUrl = sharingUrl,
                                    tag = categoryTag,
                                    secondaryEntityRoleMapId = assetMap.entitydata?.find { entity -> entity.priority == 2 }?.entityRoleMapId
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                VideosItemViewType.BANNER -> {
                    if (module.metaInfo == null)
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.Banner(
                            title = module.displayTitle.orEmpty(),
                            bannerImage = "",//configManager.getBaseUrl() + module.metaInfo.bannerImage,
                            bannerLink = module.metaInfo?.bannerLink ?: "",
                        )
                }

                VideosItemViewType.BTS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.BTS(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                VideosItemViewType.FILMS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.Films(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                VideosItemViewType.SHORTS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.Shorts(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                VideosItemViewType.HIGHLIGHTS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.Highlights(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                VideosItemViewType.SUGGESTIONS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.Suggestions(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                            entityData = listingEntityDataMapper.toDomain(module)
                        )
                }

                else -> VideosListingItem.Unknown
            }

        }.orEmpty()

    }

    private fun getWidgetType(componentName: String?, layoutType: String?): VideosItemViewType {
        return when {
            componentName == Component.SI_SHOWCASE.componentName && layoutType == WidgetView.LAYOUT_01 -> VideosItemViewType.CAROUSEL
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> VideosItemViewType.BTS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_02 -> VideosItemViewType.FILMS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_03 -> VideosItemViewType.HIGHLIGHTS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_05 -> VideosItemViewType.SHORTS
            componentName == Component.SI_ADS.componentName && layoutType == WidgetView.LAYOUT_04 -> VideosItemViewType.BANNER
//            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> VideosItemViewType.SUGGESTIONS
            else -> VideosItemViewType.UNKNOWN
        }
    }

}