package com.example.feature_video_listing.data.remote

interface VideoDetailConfig {
    fun getContentSharingVideoUrl(baseUrl : String,
                             entityCategory: String?,
                             titleAlias: String?) : String
    fun getContentImageVideoUrl1(imagePath: String?,
                            imageName: String?,
                            imageRatio: String? = null) : String
}