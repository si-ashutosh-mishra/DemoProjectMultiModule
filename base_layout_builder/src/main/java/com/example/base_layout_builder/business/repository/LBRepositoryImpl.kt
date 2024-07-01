package com.example.base_layout_builder.business.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.BaseResponse
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.base_layout_builder.data.model.layoutbuilder.Content
import com.example.base_layout_builder.data.repository.LBRepository
import com.example.base_layout_builder.data.service.LBService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LBRepositoryImpl @Inject constructor(
    private val lbService: LBService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : LBRepository {
    override fun getLBListing(url: String): Flow<Resource<Content?>> {
        return flow {

            val response = safeApiCall(ioDispatcher) {
                lbService.getLBPageListing(url = url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, Content?>(
                response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<Content?> {
                    return Resource.Success(data = resultObj.content)
                }
            }.getResult()

            emit(resource)
        }
    }
}