package com.example.photo_listing.data.mapper

import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.lb_content_listing.business.domain.model.AssetUtils
import com.example.lb_content_listing.data.mapper.AssetItemEntityMapper
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import com.example.photo_listing.business.listing.BannerItem
import com.example.photo_listing.business.model.Component
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.business.model.WidgetView
import com.example.photo_listing.data.remote.PhotoListingConfig
import com.example.photo_listing.presentation.PhotoItemViewType
import javax.inject.Inject

class PhotoModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper,
    private val listingEntityDataMapper: ListingEntityDataMapper,
    private val photoListingConfig: PhotoListingConfig
) :
    EntityMapper<List<Module>?, List<PhotoListingItem>?> {

    override fun toDomain(entity: List<Module>?): List<PhotoListingItem>? {

        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {

                PhotoItemViewType.CAROUSEL -> {
                    if (module.widgetData?.data?.assetMap.isNullOrEmpty())
                        PhotoListingItem.Unknown
                    else
                        PhotoListingItem.Carousel(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.data?.assetMap?.map { assetMap ->
                                val entityDataPriority1 =
                                    assetMap.entitydata?.find { it.priority == 1 }
                                val entityDataPriority2 =
                                    assetMap.entitydata?.find { it.priority == 2 }
                                val sharingUrl = ""/*configManager.getContentSharingUrl(
                                    baseUrl = AssetUtils.getSharingBaseUrl(
                                        primaryRoleMapId = entityDataPriority1?.entityRoleMapId,
                                        sharingDomainData = configManager.getContentSharingDetails()
                                    ),
                                    entityCategory = entityDataPriority2?.canonical,
                                    titleAlias = assetMap.assetMeta?.titleAlias.orEmpty()
                                )*/
                                BannerItem(
                                    assetId = assetMap.assetId,
                                    title = assetMap.assetMeta?.title,
                                    bannerImageUrl = photoListingConfig.getCorousalImageUrl(
                                        imagePath = assetMap.assetMeta?.imagePath ?: "",
                                        imageName = assetMap.assetMeta?.imageName ?: "",
                                        imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                    ),
                                    titleAlias = assetMap.assetMeta?.titleAlias,
                                    beautifiedDuration = CalendarUtils.getPublishedDuration(
                                        dateString = assetMap.publishDate,
                                        dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST
                                    ),
                                    reactCount = null,
                                    assetType = AssetUtils.getAssetType(
                                        assetTypeId = assetMap.assetType
                                    ),
                                    sharingUrl = sharingUrl,
                                    albumCount = assetMap.album_count
                                )
                            }.orEmpty()
                        )
                }

                PhotoItemViewType.BANNER -> {
                    if (module.metaInfo == null)
                        PhotoListingItem.Unknown
                    else
                        PhotoListingItem.Banner(
                            title = module.displayTitle.orEmpty(),
                            bannerImage = "",/*configManager.getBaseUrl() + module.metaInfo.bannerImage*/
                            bannerLink = module.metaInfo?.bannerLink ?: "",
                        )
                }

                PhotoItemViewType.MATCHPHOTOS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        PhotoListingItem.Unknown
                    else
                        PhotoListingItem.MatchPhotos(
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

                PhotoItemViewType.TRAINING -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        PhotoListingItem.Unknown
                    else
                        PhotoListingItem.Training(
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

                PhotoItemViewType.BEHINDSCENES -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        PhotoListingItem.Unknown
                    else
                        PhotoListingItem.BehindScene(
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

                else -> PhotoListingItem.Unknown
            }

        }.orEmpty()

    }

    private fun getWidgetType(componentName: String?, layoutType: String?): PhotoItemViewType {
        return when {
            componentName == Component.SI_SHOWCASE.componentName && layoutType == WidgetView.LAYOUT_03 -> PhotoItemViewType.CAROUSEL
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> PhotoItemViewType.MATCHPHOTOS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_02 -> PhotoItemViewType.TRAINING
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_03 -> PhotoItemViewType.BEHINDSCENES
            componentName == Component.SI_ADS.componentName && layoutType == WidgetView.LAYOUT_04 -> PhotoItemViewType.BANNER
            else -> PhotoItemViewType.UNKNOWN
        }
    }

}