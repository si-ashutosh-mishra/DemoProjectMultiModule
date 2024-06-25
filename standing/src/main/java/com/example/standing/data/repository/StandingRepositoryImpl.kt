package com.example.standing.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.standing.business.domain.model.standing.IPLStandings
import com.example.standing.business.repository.StandingRepository
import com.example.standing.data.mapper.StandingEntityMapper
import com.example.standing.data.model.standings.StandingEntity
import com.example.standing.data.model.standings.StandingsData
import com.example.standing.data.service.StandingService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StandingRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val standingEntityMapper: StandingEntityMapper,
    private val standingService: StandingService,
) : StandingRepository {
    override fun getStandingData(
        url: String,
        isShowForm: Boolean,
        isSwapRequired: Boolean,
        teamCount: Int?,
        swapPosition: Int?,
        currentTeamId: Int?
    ): Flow<Resource<List<IPLStandings?>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                standingService.getStandingsData(url = url)
            }
            val resource = object : ApiResultHandler<StandingsData, List<IPLStandings?>>(response) {
                override suspend fun handleSuccess(resultObj: StandingsData): Resource<List<IPLStandings?>?> {

                    val list = resultObj.standings?.stage1?.groups?.firstOrNull()
                    return Resource.Success(
                        standingEntityMapper.toDomain(
                            StandingEntity(
                                teamList = list?.team,
                                isShowForm = isShowForm,
                                requiredTeamCount = teamCount,
                                isSwapRequired = isSwapRequired,
                                swapPosition = swapPosition,
                                currentTeamId = currentTeamId
                            )
                        )
                    )
                }
            }.getResult()
            emit(resource)
        }
    }
}