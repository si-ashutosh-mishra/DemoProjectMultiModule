package com.example.photo_listing.data.service

import com.example.lb_content_listing.data.model.layoutbuilder.Content
import com.example.photo_listing.data.model.AssetContent
import com.example.photo_listing.data.model.BaseResponse
import com.example.photo_listing.data.model.PhotoDataEntity
import retrofit2.http.GET
import retrofit2.http.Url

interface PhotoDetailsService {

    @GET
    suspend fun getPhotoDetails(@Url url: String): BaseResponse<AssetContent<PhotoDataEntity>>

    @GET
    suspend fun getMorePhotos(@Url url: String) : BaseResponse<Content>
}