package com.example.content_listing.business.repository

import com.example.base.helper.Resource
import com.example.content_listing.business.domain.model.AssetItem
import kotlinx.coroutines.flow.Flow


interface ContentListingRepository {

    fun getEntityListing(
        url: String, imageRatio: String
    ): Flow<Resource<List<AssetItem>?>>


}