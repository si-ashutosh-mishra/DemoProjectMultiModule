package com.example.photo_listing.data.repository

import com.example.base.di.IoDispatcher
import com.example.base.helper.ApiResult
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.photo_listing.business.domain.model.reaction.ReactionCount
import com.example.photo_listing.business.domain.model.reaction.ReactionCountRequest
import com.example.photo_listing.business.domain.model.userreaction.UserReaction
import com.example.photo_listing.business.repository.GetReactionRepository
import com.example.photo_listing.data.service.ListingService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReactionRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetReactionRepository {

   /* override fun getReactionCount(assetList: List<String>): Flow<Resource<ReactionCount>> {
        return flow {
            val url = "https://stg-kkr.sportz.io/flrp/services/content/get-reaction-count-v1?buster=1719154591291"//configManager.getReactionCountUrl() + getQueryBuster().firstOrNull()
           // val teamName = configManager.getAppType()
            val result = safeApiCall(ioDispatcher) {
                listingService.getReactionCount(
                    gameName = "kkrappportal",
                    userGuid = ""*//*sessionStoreManager.getUserToken().firstOrNull().toString()*//*,
                    url =url,
                    reactionCountRequest = ReactionCountRequest(assetList, "2"*//*getAppSubType(teamName)*//*)
                )
            }
            when (result) {
                is ApiResult.Success -> {
                    if (result.data?.meta?.statusCode == 200) emit(Resource.Success(result.data))
                }
                is ApiResult.GenericError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            result.code, result.message ?: ""
                        )
                    )
                )
                is ApiResult.NetworkError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            null, result.message ?: ""
                        )
                    )
                )
            }
        }
    }

    override fun getUserReactionCount(assetList: List<String>): Flow<Resource<UserReaction>> {
        return flow {
            val url = "https://stg-kkr.sportz.io/flrp/services/content/get-user-reaction-v1?buster=1719154591291"*//*configManager.getUserReactionUrl() + getQueryBuster().firstOrNull()*//*
            val teamName = "" *//*configManager.getAppType()*//*
            val result = safeApiCall(ioDispatcher) {
                listingService.getUserReactionCount(
                    gameName = "kkrappportal",
                    userGuid = ""*//*sessionStoreManager.getUserToken().firstOrNull().toString()*//*,
                    url = url,
                    reactionCountRequest = ReactionCountRequest(assetList, "2"*//*getAppSubType(teamName)*//*)
                )
            }
            when (result) {
                is ApiResult.Success -> {
                    if (result.data?.meta?.statusCode == 200) emit(Resource.Success(result.data))
                }
                is ApiResult.GenericError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            result.code, result.message ?: ""
                        )
                    )
                )
                is ApiResult.NetworkError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            null, result.message ?: ""
                        )
                    )
                )
            }
        }
    }*/

    /*private fun getQueryBuster(): Flow<String> {
        *//*return flow {
            val buster = sessionStoreManager.getBuster().firstOrNull() ?: System.currentTimeMillis()
                .toString()
            sessionStoreManager.setBuster(buster)
            emit("?buster=$buster")
        }*//*
    }*/
}