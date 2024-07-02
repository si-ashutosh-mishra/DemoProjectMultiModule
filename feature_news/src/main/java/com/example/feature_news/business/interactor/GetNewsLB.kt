package com.example.feature_news.business.interactor

import com.example.base.helper.Resource
import com.example.feature_news.business.domain.model.news.NewsListingItem
import com.example.feature_news.data.mapper.NewsModuleEntityMapper
import com.example.lb_content_listing.business.repository.LBRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetNewsLB @Inject constructor(
    private val lbRepository: LBRepository,
    private val newsModuleEntityMapper: NewsModuleEntityMapper,
){
    operator fun invoke(url: String): Flow<Resource<List<NewsListingItem>>> {
        return flow{
            val result = lbRepository.getLBListing(url).first{ it !is Resource.Loading }.data

            emit(Resource.Success(data = newsModuleEntityMapper.toDomain(result)
                ?.filter { it !is NewsListingItem.Unknown }))
        }
    }

}