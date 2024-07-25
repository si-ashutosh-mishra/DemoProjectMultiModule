package com.example.feature_video_listing.business.mapper

import com.example.base.helper.EntityMapper
import com.example.base.utils.CalendarUtils
import com.example.feature_video_listing.business.model.VideoDetails
import com.example.feature_video_listing.data.model.VideoDataEntity
import com.example.lb_content_listing.data.mapper.AssetItemEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoDataEntityMapper @Inject constructor(
   // private val configManager: ConfigManager,
    private val assetItemEntityMapper: AssetItemEntityMapper,
) : EntityMapper<VideoDataEntity, VideoDetails> {
    override fun toDomain(entity: VideoDataEntity): VideoDetails {

        val entityDataPriority1 = entity.entityData?.find { it.priority == 1 }
        val entityDataPriority2 = entity.entityData?.find { it.priority == 2 }

        val tagsId = entity.entityData?.map { it.entityTypeId }?.joinToString(",")
        val tagsName = entity.entityData?.map { it.entityTypeName }?.joinToString(",")

        val categoryTag = entityDataPriority2?.entDispName ?: "Video"
        val sharingUrl = ""/*configManager.getContentSharingUrl(
            baseUrl = AssetUtils.getSharingBaseUrl(
                primaryRoleMapId = entityDataPriority1?.entityRoleMapId,
                sharingDomainData = configManager.getContentSharingDetails()
            ),
            entityCategory = entityDataPriority2?.canonical ?: "/videos",
            titleAlias = entity.slugUrl.orEmpty()
        )*/

        return VideoDetails(
            title = entity.title,
            guid = entity.guid,
            videoId = entity.videoId,
            desc = entity.desc,
            videoUrl = entity.videoUrl,
            hlsUrl = entity.hlsUrl,
            imageName = entity.imageFileName,
            imagePath = entity.imagePath,
            imageUrl = "",/*configManager.getContentImageUrl(
                imagePath = entity.imagePath,
                imageName = entity.imageFileName
            )*/
            relatedVideo = entity.relatedVideoDataEntity?.items?.map {
                assetItemEntityMapper.toDomain(it)
            },
            publishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_DETAILS,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            categoryTag = categoryTag,
            sharingUrl = sharingUrl,
            duration = entity.duration.toString(),
            contentSourceId = entity.contentSourceId,
            beautifiedDuration = null,
            tagsId = tagsId,
            tagsName = tagsName,
            assetType = entity.assetType,
            secondaryEntityRoleMapId = entityDataPriority2?.entityRoleMapId
        )
    }
}