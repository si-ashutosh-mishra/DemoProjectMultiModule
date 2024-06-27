package com.example.lb_content_listing.business.repository

import com.example.base.helper.Resource
import com.example.lb_content_listing.business.domain.model.AssetItem
import kotlinx.coroutines.flow.Flow


interface ContentListingRepository {

    fun getEntityListing(
        url: String, imageRatio: String
    ): Flow<Resource<List<AssetItem>?>>


}