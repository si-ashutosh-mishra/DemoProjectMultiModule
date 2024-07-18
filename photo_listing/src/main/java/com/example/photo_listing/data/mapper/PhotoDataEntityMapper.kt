package com.example.photo_listing.data.mapper

import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.photo_listing.business.model.PhotoDetails
import com.example.photo_listing.business.model.PhotoItem
import com.example.photo_listing.data.model.PhotoDataEntity
import com.example.photo_listing.data.remote.PhotoDetailsConfig
import com.example.photo_listing.data.remote.PhotoListingConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoDataEntityMapper @Inject constructor(
    private val configManager: PhotoDetailsConfig,
) : EntityMapper<PhotoDataEntity, PhotoDetails> {

    override fun toDomain(entity: PhotoDataEntity): PhotoDetails {

        val entityDataPriority1 = entity.entityData?.find { it.priority == 1 }
        val entityDataPriority2 = entity.entityData?.find { it.priority == 2 }
        val tagsId = entity.entityData?.map { it.entityTypeId }?.joinToString(",")
        val tagsName = entity.entityData?.map { it.entityTypeName }?.joinToString(",")
        val categoryTag = entityDataPriority2?.entDispName ?: "Photo"
        val sharingUrl = ""/*configManager.getContentSharingUrl(
            baseUrl = AssetUtils.getSharingBaseUrl(primaryRoleMapId = entityDataPriority1?.entityRoleMapId, sharingDomainData = configManager.getContentSharingDetails()),
            entityCategory = entityDataPriority2?.canonical ?: "/photos",
            titleAlias = entity.slugUrl.orEmpty()
        )*/
        return PhotoDetails(
            title = entity.title,
            guid = entity.guid,
            albumId = entity.albumId,
            albumDesc = entity.albumDesc,
            albumItems = entity.photos?.map { image ->
                PhotoItem(
                    image_id = image.dataEntity?.imageId,
                    title = image.dataEntity?.title,
                    imageUrl = configManager.getContentImageUrl1(
                        imagePath = image.dataEntity?.imagePath ?: "",
                        imageName = image.dataEntity?.imageName ?: "",
                        ""
                    ),
                    caption = image.dataEntity?.imageCaption,
                    desc = image.dataEntity?.imageDesc,
                    isCover = image.dataEntity?.isCover == "1"
                )
            }?.filter { !it.isCover },
            publishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_DETAILS,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            categoryTag = categoryTag,
            sharingUrl = sharingUrl,
            tagsId = tagsId,
            tagsName = tagsName,
            assetType = entity.assetType
        )
    }


}