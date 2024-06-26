package com.example.feature_news.data.service

import com.example.feature_news.data.model.BaseResponse
import com.example.feature_news.data.model.layoutbuilder.Content
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsService {
    @GET
    suspend fun getNewsPageListing(@Url url: String): BaseResponse<Content>
}