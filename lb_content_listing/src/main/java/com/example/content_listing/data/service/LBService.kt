package com.example.content_listing.data.service


import com.example.base.helper.BaseResponse
import com.example.content_listing.data.model.Content
import retrofit2.http.GET
import retrofit2.http.Url

interface LBService {

    @GET
    suspend fun getLBPageListing(@Url url: String): BaseResponse<Content>

}