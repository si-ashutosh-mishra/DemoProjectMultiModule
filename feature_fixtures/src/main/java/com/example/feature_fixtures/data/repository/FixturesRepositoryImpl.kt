package com.example.feature_fixtures.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.feature_fixtures.business.domain.model.fixture.FixtureItems
import com.example.feature_fixtures.business.repository.FixtureRepository
import com.example.feature_fixtures.data.mapper.MasterHeadEntityListingMapper
import com.example.feature_fixtures.data.mapper.MasterHeadEntityMapper
import com.example.feature_fixtures.data.model.mastheadscore.MasterHeadResponse
import com.example.feature_fixtures.data.service.FixturesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val fixturesService: FixturesService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val masterHeadEntityMapper: MasterHeadEntityMapper,
    private val masterHeadEntityListingMapper: MasterHeadEntityListingMapper
):FixtureRepository {

    override fun getMatchMastHead(
        type: Int,
        url: String?,
        teamId: String?
    ): Flow<Resource<FixtureItems?>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(ioDispatcher) {
                fixturesService.getMatchMasterHead(url?:"")
            }
            val resource =
                object : ApiResultHandler<MasterHeadResponse, FixtureItems>(result) {
                    override suspend fun handleSuccess(resultObj: MasterHeadResponse): Resource<FixtureItems?> {
                        val fixtureItems = when (type) {
                            1 -> {
                                masterHeadEntityMapper.toDomain(resultObj)
                            }
                            else -> {
                                masterHeadEntityListingMapper.toDomain(resultObj.copy(
                                    teamId = teamId
                                ))
                            }
                        }
                        return Resource.Success(fixtureItems)
                    }
                }.getResult()
            emit(resource)
        }
    }
}