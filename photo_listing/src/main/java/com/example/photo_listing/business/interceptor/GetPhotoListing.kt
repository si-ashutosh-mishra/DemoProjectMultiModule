package com.example.photo_listing.business.interceptor

import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.lb_content_listing.business.repository.LBRepository
import com.example.lb_content_listing.data.service.LBService
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.data.mapper.PhotoModuleEntityMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetPhotoListing @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
   private val lbRepository: LBRepository,
    private val photoModuleEntityMapper: PhotoModuleEntityMapper
) {

    operator fun invoke(url: String): Flow<Resource<List<PhotoListingItem>>> {
        return flow{
            val result = lbRepository.getLBListing(url).first{ it !is Resource.Loading }.data

            emit(Resource.Success(data = photoModuleEntityMapper.toDomain(result)
                ?.filter { it !is PhotoListingItem.Unknown }))
        }
    }

}