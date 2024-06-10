package com.example.feature_fixtures.business.repository

import com.example.base.helper.Resource
import com.example.feature_fixtures.business.domain.model.fixture.FixtureItems
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {

    fun getMatchMastHead(
        type: Int,
        url:String?,
        teamId:String?
    ): Flow<Resource<FixtureItems?>>


}