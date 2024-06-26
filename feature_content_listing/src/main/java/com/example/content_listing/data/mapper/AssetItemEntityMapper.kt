package com.example.content_listing.data.mapper


import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.content_listing.business.domain.model.AssetItem
import com.example.content_listing.data.model.AssetItemEntity
import com.example.content_listing.data.remote.ContentListingConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetItemEntityMapper @Inject constructor(
    private val contentListingConfigContract: ContentListingConfigContract
) : EntityMapper<AssetItemEntity, AssetItem> {

    override fun toDomain(entity: AssetItemEntity): AssetItem {
        return toDomain(entity = entity, imageRatio = "16-9")
    }

    fun toDomain(entity: AssetItemEntity, imageRatio: String?): AssetItem {

        var beautifiedTime = "00:00"

        beautifiedTime = try {
            if (entity.duration?.contains(
                    ":", false
                ) == true
            ) entity.duration else secondsToHoursMinutesSeconds(
                entity.duration?.toLong() ?: 0
            )
        } catch (e: Exception) {
            "00:00"
        }
        return AssetItem(
            assetId = entity.assetId,
            beautifiedDuration = CalendarUtils.convertDateStringToSpecifiedDateString(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            assetGuid = entity.assetGuid,
            assetTitle = entity.assetTitle,
            titleAlias = entity.titleAlias,
            assetTypeId = entity.assetTypeId,
            secondaryEntityRoleMapId = entity.secondaryEntityRoleMapId,
            imageUrl = contentListingConfigContract.getContentImageUrl(
                imagePath = entity.imagePath ?: "",
                imageName = entity.imageFileName ?: "",
                imageRatio = imageRatio
            ),
            sharingUrl = contentListingConfigContract.getContentSharingUrl(
                entityCategory = entity.secCanonicalUrl.orEmpty(),
                titleAlias = entity.titleAlias.orEmpty()
            ),
            reelSharingUrl = contentListingConfigContract.getReelsSharingUrl(
                entityCategory = entity.secEntUrl.orEmpty(),
                titleAlias = entity.titleAlias.orEmpty(),
                assetId = entity.assetId,
                assetTypeId = entity.assetTypeId
            ),
            totalAssets = entity.totalAssets?.let { it.toInt() + 1 }.toString(),
            totalReacts = null,
            description = entity.description,
            offer = entity.offer,
            price = entity.price,
            discountPrice = entity.discountPrice,
            externalLink = entity.externalLink,
            contentSourceId = entity.contentSourceId,
            bannerImage = entity.bannerImage,
            bannerLink = entity.bannerUrl,
            publishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            webViewUrl = entity.webviewUrl,
            isWebView = entity.isWebview,
            inAppBrowser = entity.in_app_browser,
            isExternalWebView = entity.is_external_webview,
            isWebAuth = entity.is_web_auth,
            primaryName = entity.primaryEntityName ?: "",
            secondaryEntityName = entity.secondaryEntityName,
            primaryEntityDisplayName = entity.priEntDispName,
            primaryEntityRoleMapId = entity.primaryEntityRoleMapId,
            duration = beautifiedTime,
            videoUrl = entity.videoUrl,
            secCanonicalUrl = entity.secCanonicalUrl ?: "",
            likesCounts = 0
        )
    }

    private fun secondsToHoursMinutesSeconds(duration: Long): String {
        val hours = duration / 3600
        val minutes = (duration % 3600) / 60
        val seconds = duration % 60
        return if (hours > 0) String.format(
            "%02d:%02d:%02d", hours, minutes, seconds
        ) else String.format("%02d:%02d", minutes, seconds)
    }
}