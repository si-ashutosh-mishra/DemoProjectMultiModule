package com.example.base_layout_builder.data.repository

import com.example.base.helper.Resource
import com.example.base_layout_builder.data.model.layoutbuilder.Content
import kotlinx.coroutines.flow.Flow

interface LBRepository {

    fun getLBListing(url: String): Flow<Resource<Content?>>


}