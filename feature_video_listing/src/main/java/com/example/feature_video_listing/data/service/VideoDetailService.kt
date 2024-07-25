package com.example.feature_video_listing.data.service

import com.example.base.helper.BaseResponse
import com.example.feature_video_listing.data.model.AssetContent
import com.example.feature_video_listing.data.model.VideoDataEntity
import com.example.lb_content_listing.data.model.layoutbuilder.Content
import retrofit2.http.GET
import retrofit2.http.Url

interface VideoDetailService {

    @GET
    suspend fun getVideoDetails(@Url url: String): BaseResponse<AssetContent<VideoDataEntity>>

    @GET
    suspend fun getMoreVideos(@Url url: String) : BaseResponse<Content>

}