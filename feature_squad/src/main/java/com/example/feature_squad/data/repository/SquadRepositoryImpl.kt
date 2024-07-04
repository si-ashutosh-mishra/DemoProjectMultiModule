package com.example.feature_squad.data.repository

import com.example.base.di.IoDispatcher
import com.example.base.helper.ApiResult
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.feature_squad.business.repository.SquadRepository
import com.example.feature_squad.data.model.CustomSquadInfo
import com.example.feature_squad.data.model.SquadList
import com.example.feature_squad.data.service.SquadService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val squadService: SquadService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): SquadRepository {

    override fun getSquadCustomFeed(url: String): Flow<Resource<CustomSquadInfo>> {
        return flow {
//            val url = "https://www.knightclub.in/static-assets/feeds/custom/en/trans.json"
            val result = safeApiCall(ioDispatcher) {
                squadService.getSquadCustomFeed(
                    url
                )
            }

            when (result) {
                is ApiResult.GenericError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            result.code,
                            result.message ?: ""
                        )
                    )
                )
                is ApiResult.NetworkError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            null,
                            result.message ?: ""
                        )
                    )
                )
                is ApiResult.Success -> {
                    emit(Resource.Success(result.data))
                }
            }
        }
    }


    override fun getSquadsListing(url: String?): Flow<Resource<SquadList>> {
        return flow {
            emit(Resource.Loading())
            val response = safeApiCall(ioDispatcher) {
                squadService.getSquadListing(
                    url?:""
//                    configManager.getSquadListingUrl(seriesId = seriesId, teamId = teamId)
                )
            }

            when (response) {
                is ApiResult.GenericError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            code = response.code, message = response.message.orEmpty()
                        )
                    )
                )

                is ApiResult.NetworkError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            code = null, message = response.message.orEmpty()
                        )
                    )
                )

                is ApiResult.Success -> emit(Resource.Success(data = response.data))
            }

        }
    }
}