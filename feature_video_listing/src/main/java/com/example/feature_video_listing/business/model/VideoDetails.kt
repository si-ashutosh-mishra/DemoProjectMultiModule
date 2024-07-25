package com.example.feature_video_listing.business.model

import com.example.lb_content_listing.business.domain.model.AssetItem
import java.io.Serializable

data class VideoDetails(
    val title: String?,
    val guid: String?,
    val videoId: Int?,
    val desc: String?,
    val videoUrl: String?,
    val hlsUrl: String?,
    val imageName: String?,
    val imagePath: String?,
    val imageUrl: String?,
    val relatedVideo: List<AssetItem>?,
    val publishedDate: String?,
    val videoUrlWithToken: String? = null,
    val categoryTag: String?,
    val sharingUrl: String?,
    val duration: String,
    val contentSourceId: String?,
    val beautifiedDuration: String?, //--:-- or 02:55
    val tagsId: String?,
    val tagsName: String?,
    val assetType: String?,
    val secondaryEntityRoleMapId:Int?
) : Serializable

enum class VideoType(val contentSourceId: String) {
    YOUTUBE("1"),
    NATIVE("10"),
    BRIGHTCOVE("33")
}
