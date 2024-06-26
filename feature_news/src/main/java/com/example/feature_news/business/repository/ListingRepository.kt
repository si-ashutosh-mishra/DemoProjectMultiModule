package com.example.feature_news.business.repository

import com.example.base.helper.Resource
import com.knightclub.app.business.domain.model.news.NewsListingItem
import kotlinx.coroutines.flow.Flow

interface ListingRepository {

    fun getNewsPageListing(): Flow<Resource<List<NewsListingItem>?>>
}