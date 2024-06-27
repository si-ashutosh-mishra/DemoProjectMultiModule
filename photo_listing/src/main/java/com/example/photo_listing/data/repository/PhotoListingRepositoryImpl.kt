package com.example.photo_listing.data.repository

import ApiResultHandler
import android.util.Log
import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.feature_fixtures.data.remote.PhotoListingConfig
import com.example.photo_listing.business.domain.model.photolisting.Content
import com.example.photo_listing.data.mapper.PhotoModuleEntityMapper
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.business.model.ReactionType
import com.example.photo_listing.business.repository.PhotosRepository
import com.example.photo_listing.data.model.BaseResponse
import com.example.photo_listing.data.service.ListingService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoListingRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val photoModuleEntityMapper: PhotoModuleEntityMapper,
    private val photoListingConfig: PhotoListingConfig
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

            val assetList = resource.data?.toList()

            val listOfIds = mutableListOf<String>()

            Log.d("ASSETLIST", "getPhotosListing: "+assetList)

            resource.data?.forEachIndexed { _, newsListingItem ->
                when (newsListingItem) {
                    is PhotoListingItem.Carousel -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    is PhotoListingItem.MatchPhotos -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    is PhotoListingItem.Training -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    is PhotoListingItem.BehindScene -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    else -> Unit
                }
            }

          /*  getReactionRepositoryImpl.getReactionCount(listOfIds)
                .collectLatest { reactionCount ->

                    when (reactionCount) {
                        is Resource.Success -> {

                            assetList?.forEachIndexed { index, photoListingItem ->

                                when (photoListingItem) {
                                    is PhotoListingItem.Carousel -> {
                                        photoListingItem.items.forEach { assetItem ->
                                            reactionCount.data?.data?.asset?.forEach {
                                                if (it?.assetId.equals(assetItem.getIdWithAssetType())) {
                                                    assetItem.reactCount = it?.reaction?.find { it.reactionId == "1" }?.count?.toString()?:"0"
                                                }
                                            }
                                        }
                                    }

                                    is PhotoListingItem.Training -> {
                                        photoListingItem.items.forEach { assetItem ->
                                            reactionCount.data?.data?.asset?.forEach {
                                                if (it?.assetId.equals(assetItem.getIdWithAssetType())) {
                                                    assetItem.totalReacts = it?.reaction?.find { it.reactionId == "1" }?.count?.toString()?:"0"
                                                }
                                            }
                                        }
                                    }

                                    is PhotoListingItem.BehindScene -> {
                                        photoListingItem.items.forEach { assetItem ->
                                            reactionCount.data?.data?.asset?.forEach {
                                                if (it?.assetId.equals(assetItem.getIdWithAssetType())) {
                                                    assetItem.totalReacts = it?.reaction?.find { it.reactionId == "1" }?.count?.toString()?:"0"
                                                }
                                            }
                                        }
                                    }

                                    else -> Unit
                                }
                            }
                        }

                        else -> Unit
                    }
                }*/

           /* getReactionRepositoryImpl.getUserReactionCount(listOfIds)
                .collectLatest { reactionCount ->

                    when (reactionCount) {
                        is Resource.Success -> {

                            assetList?.forEachIndexed { index, photoListingItem ->

                                when (photoListingItem) {
                                    is PhotoListingItem.Carousel -> {
                                        photoListingItem.items.forEach { assetItem ->
                                            reactionCount.data?.data?.assetReaction?.forEach {
                                                if (it.assetId.equals(assetItem.getIdWithAssetType())) {
                                                    assetItem.isLiked = it.reactionId.equals(
                                                        ReactionType.LIKE.id)
                                                }
                                            }
                                        }
                                    }

                                    is PhotoListingItem.Training -> {
                                        photoListingItem.items.forEach { assetItem ->
                                            reactionCount.data?.data?.assetReaction?.forEach {
                                                if (it.assetId.equals(assetItem.getIdWithAssetType())) {
                                                    assetItem.isLiked = it.reactionId.equals(ReactionType.LIKE.id)
                                                }
                                            }
                                        }
                                    }

                                    is PhotoListingItem.BehindScene -> {
                                        photoListingItem.items.forEach { assetItem ->
                                            reactionCount.data?.data?.assetReaction?.forEach {
                                                if (it.assetId.equals(assetItem.getIdWithAssetType())) {
                                                    assetItem.isLiked = it.reactionId.equals(ReactionType.LIKE.id)
                                                }
                                            }
                                        }
                                    }

                                    else -> Unit
                                }
                            }
                        }

                        else -> Unit
                    }
                }*/

            emit(Resource.Success(assetList))
        }
    }
}