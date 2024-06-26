package com.example.photo_listing.business.interceptor

import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.business.repository.PhotosRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetPhotoListing @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val photosRepository: PhotosRepository
) {

    operator fun invoke(
        url: String
    ): Flow<Resource<List<PhotoListingItem>?>> {
        return photosRepository.getPhotosListing(url)
    }
}