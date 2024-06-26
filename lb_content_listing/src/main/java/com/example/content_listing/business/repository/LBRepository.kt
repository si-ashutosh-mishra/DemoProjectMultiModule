package com.example.content_listing.business.repository

import com.example.base.helper.Resource
import com.example.content_listing.data.model.Content
import kotlinx.coroutines.flow.Flow

interface LBRepository {

    fun getLBListing(url: String): Flow<Resource<Content?>>


}