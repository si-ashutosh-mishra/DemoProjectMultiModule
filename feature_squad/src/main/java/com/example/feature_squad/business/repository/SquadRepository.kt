package com.example.feature_squad.business.repository

import com.example.base.helper.Resource
import com.example.feature_squad.data.model.CustomSquadInfo
import com.example.feature_squad.data.model.SquadList
import kotlinx.coroutines.flow.Flow

interface SquadRepository {

    fun getSquadCustomFeed(): Flow<Resource<CustomSquadInfo>>
    fun getSquadsListing(url: String?): Flow<Resource<SquadList>>

}