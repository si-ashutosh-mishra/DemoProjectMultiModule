package com.example.photo_listing.data.mapper

import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.photo_listing.business.domain.model.photolisting.AssetItemEntity
import com.example.photo_listing.business.listing.AssetItem
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
        val sharingDomainData = ""/*configManager.getContentSharingDetails()*/
        return AssetItem(
            assetId = entity.assetId,
            beautifiedDuration = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST
            ),
            publishedDate=entity.publishedDate,
            assetGuid = entity.assetGuid,
            assetTitle = entity.assetTitle,
            titleAlias = entity.titleAlias,
            assetTypeId = entity.assetTypeId,
            secondaryEntityRoleMapId = entity.secondaryEntityRoleMapId,
            imageUrl = ""/*configManager.getContentImageUrl(
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