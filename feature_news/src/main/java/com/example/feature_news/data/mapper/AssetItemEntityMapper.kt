package com.knightclub.app.business.mapper

import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.feature_news.business.domain.model.listing.AssetItem
import com.knightclub.app.business.domain.model.listing.AssetUtils
import com.knightclub.app.data.model.layoutbuilder.AssetItemEntity
import com.knightclub.app.data.model.loyalty.BannerRedirectionModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetItemEntityMapper @Inject constructor(
    //private val configManager: ConfigManager,
) : EntityMapper<AssetItemEntity, AssetItem> {

    override fun toDomain(entity: AssetItemEntity): AssetItem {
        return toDomain(entity = entity, imageRatio = null)
    }

    fun toDomain(entity: AssetItemEntity, imageRatio: String?): AssetItem {
        val sharingDomainData = ""//configManager.getContentSharingDetails()
        return AssetItem(
            assetId = entity.assetId,
            beautifiedDuration = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST
            ),
            publishedDate= CalendarUtils.convertDateStringToSpecifiedDateString(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_DATE_FORMAT,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            assetGuid = entity.assetGuid,
            assetTitle = entity.assetTitle,
            titleAlias = entity.titleAlias,
            assetTypeId = entity.assetTypeId,
            secondaryEntityRoleMapId = entity.secondaryEntityRoleMapId,
            imageUrl = "https://www.knightclub.in/static-assets/waf-images/"
            +"${entity.imagePath}"+"${entity.imageFileName}"+"?v=1.30"
            /*configManager.getContentImageUrl(
                imagePath = entity.imagePath ?: "",
                imageName = entity.imageFileName ?: "",
                imageRatio = imageRatio
            )*/,
            sharingUrl = ""/*configManager.getContentSharingUrl(
                baseUrl = AssetUtils.getSharingBaseUrl(primaryRoleMapId = entity.primaryEntityRoleMapId, sharingDomainData = sharingDomainData),
                entityCategory = entity.secEntUrl.orEmpty(),
                titleAlias = entity.titleAlias.orEmpty()
            )*/,
            totalAssets = entity.totalAssets,
            totalReacts = null,
            description = entity.description,
            offer = entity.offer,
            price = entity.price,
            discountPrice = entity.discountPrice,
            externalLink = entity.externalLink,
            contentSourceId = entity.contentSourceId,
            infoUrl = entity.infoUrl,
            tag = entity.priEntDispName,
            redirectionPayload = entity.getCustomJson(),
            shortDesc = entity.shortDesc,
            videoUrl = entity.videoUrl
        )
    }
}