package com.example.photo_listing.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.lb_content_listing.data.model.layoutbuilder.Content
import com.example.photo_listing.business.model.PhotoDetails
import com.example.photo_listing.business.repository.PhotoDetailRepository
import com.example.photo_listing.data.mapper.AssetItemEntityMapper
import com.example.photo_listing.data.mapper.PhotoDataEntityMapper
import com.example.photo_listing.data.model.AssetContent
import com.example.photo_listing.data.model.BaseResponse
import com.example.photo_listing.data.model.PhotoDataEntity
import com.example.photo_listing.data.service.PhotoDetailsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoDetailsImpl @Inject constructor(
    val photoDetailsService: PhotoDetailsService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    val photoDataEntityMapper: PhotoDataEntityMapper,
    val assetItemEntityMapper: AssetItemEntityMapper
) :PhotoDetailRepository{
    override fun getPhotoDetails(titleAlias : String): Flow<Resource<PhotoDetails?>> {
        return flow {
            emit(Resource.Loading())
            val url = "https://stg-kc.sportz.io/apiv3/photo/{title_alias}?is_app=1".replace("{title_alias}",titleAlias)
            val response = safeApiCall(ioDispatcher){
                photoDetailsService.getPhotoDetails(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<AssetContent<PhotoDataEntity>>,PhotoDetails>
                (response){
                override suspend fun handleSuccess(resultObj: BaseResponse<AssetContent<PhotoDataEntity>>): Resource<PhotoDetails?> {
                    return if (resultObj.status == 200){
                        Resource.Success(
                            resultObj.content?.data?.let {
                                photoDataEntityMapper.toDomain(it)
                            }
                        )
                    }else{
                        Resource.Error(NetworkThrowable(resultObj.status,""))
                    }
                }
            }.getResult()
            emit(resource)
        }
    }

    override fun getMorePhotos(url: String): Flow<Resource<List<AssetItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher){
                photoDetailsService.getMorePhotos(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<AssetItem>>(response){
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<AssetItem>?> {
                    return Resource.Success(data = resultObj.content?.assetItemEntities?.map { it->
                        assetItemEntityMapper.toDomain(it,"3-4")
                    })
                }
            }.getResult()
            emit(resource)
        }
    }

    override fun getMoreVideos(url: String): Flow<Resource<List<AssetItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher){
                photoDetailsService.getMorePhotos(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<AssetItem>>(response){
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<AssetItem>?> {
                    return Resource.Success(data = resultObj.content?.assetItemEntities?.map { it->
                        assetItemEntityMapper.toDomain(it,"3-4")
                    })
                }
            }.getResult()
            emit(resource)
        }
    }

    override fun getMoreNews(url: String): Flow<Resource<List<AssetItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher){
                photoDetailsService.getMorePhotos(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<AssetItem>>(response){
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<AssetItem>?> {
                    return Resource.Success(data = resultObj.content?.assetItemEntities?.map { it->
                        assetItemEntityMapper.toDomain(it,"3-4")
                    })
                }
            }.getResult()
            emit(resource)
        }
    }
}