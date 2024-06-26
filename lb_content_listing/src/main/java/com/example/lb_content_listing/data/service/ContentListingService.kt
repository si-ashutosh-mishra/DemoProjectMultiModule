package com.example.lb_content_listing.data.service


import com.example.base.helper.BaseResponse
import com.example.lb_content_listing.data.model.layoutbuilder.Content
import retrofit2.http.GET
import retrofit2.http.Url

interface ContentListingService {

    @GET
    suspend fun getContentListing(@Url url: String): BaseResponse<Content>
}