package com.example.photo_listing.business.repository

import com.example.base.helper.Resource
import com.example.photo_listing.business.model.PhotoListingItem
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotosListing(url : String):Flow<Resource<List<PhotoListingItem>?>>

}