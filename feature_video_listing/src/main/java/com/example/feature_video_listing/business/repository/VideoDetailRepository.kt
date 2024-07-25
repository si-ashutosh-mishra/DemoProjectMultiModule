package com.example.feature_video_listing.business.repository

import com.example.base.helper.Resource
import com.example.feature_video_listing.business.model.VideoDetails
import com.example.lb_content_listing.business.domain.model.AssetItem
import kotlinx.coroutines.flow.Flow

interface VideoDetailRepository {

    fun getVideoDetailRepository(url : String) : Flow<Resource<VideoDetails?>>

    fun getMoreVideos(url: String) : Flow<Resource<List<AssetItem>?>>

}