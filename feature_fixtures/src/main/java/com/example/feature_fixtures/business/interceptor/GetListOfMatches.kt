package com.example.feature_fixtures.business.interceptor

import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.feature_fixtures.business.domain.model.fixture.FixtureItems
import com.example.feature_fixtures.business.repository.FixtureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetListOfMatches @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fixtureRepository: FixtureRepository
) {

    operator fun invoke(
        type: Int,
        url: String?,
        teamId: String?,
        itemCount: Int
    ): Flow<Resource<FixtureItems?>> {
        return fixtureRepository.getMatchMastHead(type, url, teamId, itemCount = itemCount)
    }
}