package com.example.base_layout_builder.data.service


import com.example.base.helper.BaseResponse
import com.example.base_layout_builder.data.model.layoutbuilder.Content
import retrofit2.http.GET
import retrofit2.http.Url

interface LBService {

    @GET
    suspend fun getLBPageListing(@Url url: String): BaseResponse<Content>

}