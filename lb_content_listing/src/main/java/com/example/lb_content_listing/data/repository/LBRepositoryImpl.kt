package com.example.lb_content_listing.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.BaseResponse
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.lb_content_listing.business.repository.LBRepository
import com.example.lb_content_listing.data.model.layoutbuilder.Content
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import com.example.lb_content_listing.data.service.LBService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LBRepositoryImpl @Inject constructor(
    private val lbService: LBService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : LBRepository {
    override fun getLBListing(url: String): Flow<Resource<List<Module>?>> {
        return flow {

            val response = safeApiCall(ioDispatcher) {
                lbService.getLBPageListing(url = url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<Module>>(
                response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<Module>?> {
                    val module = resultObj.content?.module?.filter { it.showInApp == 1 }
                        ?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }.orEmpty()
                    return Resource.Success(data = module)
                }
            }.getResult()

            emit(resource)
        }
    }
}