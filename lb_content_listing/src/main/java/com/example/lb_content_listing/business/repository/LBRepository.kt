package com.example.lb_content_listing.business.repository

import com.example.base.helper.Resource
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import kotlinx.coroutines.flow.Flow

interface LBRepository {

    fun getLBListing(url: String): Flow<Resource<List<Module>?>>


}