package com.example.photo_listing.business.repository

import com.example.base.helper.Resource
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.business.model.PhotoDetails
import kotlinx.coroutines.flow.Flow

interface PhotoDetailRepository {

    fun getPhotoDetails(titleAlias : String) : Flow<Resource<PhotoDetails?>>

    fun getMorePhotos(url : String) : Flow<Resource<List<AssetItem>?>>

    fun getMoreVideos(url: String) : Flow<Resource<List<AssetItem>?>>

    fun getMoreNews(url: String) : Flow<Resource<List<AssetItem>?>>

}