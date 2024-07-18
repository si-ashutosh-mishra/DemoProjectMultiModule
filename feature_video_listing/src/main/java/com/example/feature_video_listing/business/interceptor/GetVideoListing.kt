package com.example.feature_video_listing.business.interceptor

import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.feature_video_listing.business.domain.VideosListingItem
import com.example.feature_video_listing.business.mapper.VideoModuleEntityMapper
import com.example.lb_content_listing.business.repository.LBRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetVideoListing @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
   private val lbRepository: LBRepository,
    private val videoModuleEntityMapper: VideoModuleEntityMapper
) {

    operator fun invoke(url: String): Flow<Resource<List<VideosListingItem>>> {
        return flow{
            val result = lbRepository.getLBListing(url).first{ it !is Resource.Loading }.data

            emit(Resource.Success(data = videoModuleEntityMapper.toDomain(result)
                ?.filter { it !is VideosListingItem.Unknown }))
        }
    }

}