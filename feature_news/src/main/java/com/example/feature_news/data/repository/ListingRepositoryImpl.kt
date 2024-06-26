package com.example.feature_news.data.repository

import ApiResultHandler
import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.base.helper.safeApiCall
import com.example.feature_news.business.repository.ListingRepository
import com.example.feature_news.data.model.BaseResponse
import com.example.feature_news.data.model.layoutbuilder.Content
import com.example.feature_news.data.service.NewsService
import com.knightclub.app.business.domain.model.news.NewsListingItem
import com.example.feature_news.data.mapper.NewsModuleEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val newsService: NewsService,
    private val newsModuleEntityMapper: NewsModuleEntityMapper,
) : ListingRepository {
    override fun getNewsPageListing(): Flow<Resource<List<NewsListingItem>?>> {
        return flow {
            emit(Resource.Loading())
            val url = "https://www.knightclub.in/apiv4/gettemplatedata?url=kkr-app-home/app-news&is_app=1"
            val response = safeApiCall(ioDispatcher){
                newsService.getNewsPageListing(url = url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<NewsListingItem>>(
                response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<NewsListingItem>?> {
                    val module = resultObj.content?.module?.filter { it.showInApp == 1 }
                        ?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }

                    return Resource.Success(data = newsModuleEntityMapper.toDomain(module)
                        ?.filter { it !is NewsListingItem.Unknown })

                }
            }.getResult()

            val assetList = resource.data?.toList()

            val listOfIds = mutableListOf<String>()

            resource.data?.forEachIndexed { _, newsListingItem ->
                when (newsListingItem) {
                    is NewsListingItem.Carousel -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    is NewsListingItem.Latest -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    is NewsListingItem.Featured -> {
                        newsListingItem.items.forEach {
                            listOfIds.add(it.assetId.toString() + "_" + it.assetType.typeId.toString())
                        }
                    }

                    else -> Unit
                }
            }

            emit(Resource.Success(assetList))
        }
    }
}