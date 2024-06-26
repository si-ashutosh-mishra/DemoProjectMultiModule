package com.example.photo_listing.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.photo_listing.business.domain.model.Content
import com.example.photo_listing.data.mapper.PhotoModuleEntityMapper
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.business.repository.PhotosRepository
import com.example.photo_listing.data.model.BaseResponse
import com.example.photo_listing.data.service.ListingService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoListingRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val photoModuleEntityMapper: PhotoModuleEntityMapper
) : PhotosRepository{
    override fun getPhotosListing(url: String): Flow<Resource<List<PhotoListingItem>?>> {
        return flow {
            emit(Resource.Loading())
            val response = safeApiCall(ioDispatcher){
                listingService.getPhotosPageListing(url)
            }
            val resource = object : ApiResultHandler<BaseResponse<Content>,List<PhotoListingItem>?>(
                response = response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<PhotoListingItem>?> {
                    val module = resultObj.content?.module?.filter { it.showInApp == 1 }?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }
                    return Resource.Success(data = photoModuleEntityMapper.toDomain(module)
                        ?.filter { it !is PhotoListingItem.Unknown })
                }
            }.getResult()
            emit(resource)
        }
    }
}