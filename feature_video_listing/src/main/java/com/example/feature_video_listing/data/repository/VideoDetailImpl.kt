package com.example.feature_video_listing.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.BaseResponse
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.feature_video_listing.business.mapper.AssetItemEntityMapper
import com.example.feature_video_listing.business.mapper.VideoDataEntityMapper
import com.example.feature_video_listing.business.model.VideoDetails
import com.example.feature_video_listing.business.repository.VideoDetailRepository
import com.example.feature_video_listing.data.model.AssetContent
import com.example.feature_video_listing.data.model.VideoDataEntity
import com.example.feature_video_listing.data.service.VideoDetailService
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.lb_content_listing.data.model.layoutbuilder.Content
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoDetailImpl @Inject constructor(
    val videoDetailService: VideoDetailService,
     @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    val videoDataEntityMapper: VideoDataEntityMapper,
    val assetItemEntityMapper: AssetItemEntityMapper
) : VideoDetailRepository {

    override fun getVideoDetailRepository(url: String): Flow<Resource<VideoDetails?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher){
                videoDetailService.getVideoDetails(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<AssetContent<VideoDataEntity>> , VideoDetails>
                (response){
                override suspend fun handleSuccess(resultObj: BaseResponse<AssetContent<VideoDataEntity>>): Resource<VideoDetails?> {
                    return if (resultObj.status == 200){
                        Resource.Success(resultObj.content?.data?.let {
                            videoDataEntityMapper.toDomain(it)
                        })
                    }else{
                        Resource.Error(NetworkThrowable(resultObj.status,""))
                    }
                }
            }.getResult()
            emit(resource)
        }
    }

    override fun getMoreVideos(url: String): Flow<Resource<List<AssetItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher){
                videoDetailService.getMoreVideos(url)
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