package com.example.content_listing.business.interactor


import com.example.base.helper.Resource
import com.example.content_listing.business.domain.model.AssetItem
import com.example.content_listing.business.repository.ContentListingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class GetContentListingUseCase @Inject constructor(
    private val contentListingRepository: ContentListingRepository,

    ) {
    operator fun invoke(url: String, imageRatio: String): Flow<Resource<List<AssetItem>?>> {
        return contentListingRepository.getEntityListing(url, imageRatio)
    }
}