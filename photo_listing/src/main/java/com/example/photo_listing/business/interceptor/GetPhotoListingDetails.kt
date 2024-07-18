package com.example.photo_listing.business.interceptor

import com.example.base.di.IoDispatcher
import com.example.base.helper.Resource
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.business.model.PhotoDetails
import com.example.photo_listing.business.repository.PhotoDetailRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetPhotoListingDetails @Inject constructor(
    private val photoDetailsRepository: PhotoDetailRepository
) {

    operator fun invoke(url : String) : Flow<Resource<PhotoDetails?>>{
       return photoDetailsRepository.getPhotoDetails(url)
    }

    fun getMorePhotos() : Flow<Resource<List<AssetItem>?>>{
        return photoDetailsRepository.getMorePhotos("https://stg-kc.sportz.io/apiv3/listing?entities=2,4,174&otherent=&exclent=7&pgnum=1&inum=10&pgsize=4")
    }

    fun getMoreVideos() : Flow<Resource<List<AssetItem>?>>{
        return photoDetailsRepository.getMoreVideos("https://stg-kc.sportz.io/apiv3/listing?entities=3,4,174&otherent=&exclent=&pgnum=1&inum=10&pgsize=10")
    }

    fun getMoreNews() : Flow<Resource<List<AssetItem>?>>{
        return photoDetailsRepository.getMoreNews("https://stg-kc.sportz.io/apiv3/listing?entities=1,4,174&otherent=&exclent=7&pgnum=1&inum=10&pgsize=10")
    }
}