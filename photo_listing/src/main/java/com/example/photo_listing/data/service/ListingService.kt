package com.example.photo_listing.data.service

import com.example.photo_listing.business.domain.model.Content
import com.example.photo_listing.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ListingService {

    @GET
    suspend fun getPhotosPageListing(@Url url: String): BaseResponse<Content>
}