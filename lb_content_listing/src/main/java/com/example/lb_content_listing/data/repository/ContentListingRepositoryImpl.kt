package com.example.lb_content_listing.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.lb_content_listing.business.repository.ContentListingRepository
import com.example.lb_content_listing.data.mapper.AssetItemEntityMapper
import com.example.base.helper.BaseResponse
import com.example.lb_content_listing.data.model.layoutbuilder.Content
import com.example.lb_content_listing.data.service.ContentListingService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ContentListingRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val contentListingService: ContentListingService,
    private val assetItemEntityMapper: AssetItemEntityMapper
) : ContentListingRepository {

    override fun getEntityListing(
        url: String, imageRatio: String
    ): Flow<Resource<List<AssetItem>?>> {
        return flow {

            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                contentListingService.getContentListing(
                    url = url
                )
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<AssetItem>>(
                response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<AssetItem>?> {
                    return if (resultObj.status == 200) {
                        Resource.Success(resultObj.content?.assetItemEntities?.map {
                            assetItemEntityMapper.toDomain(it, imageRatio)
                        })
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                code = null, message = "Failed to load!!"
                            )
                        )
                    }
                }
            }.getResult()

            emit(resource)
        }
    }

}