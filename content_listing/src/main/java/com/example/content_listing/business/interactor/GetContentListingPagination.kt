package com.example.content_listing.business.interactor


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.base.helper.Resource
import com.example.content_listing.business.domain.model.AssetItem
import com.example.content_listing.business.interactor.paging.AssetPagingSource
import com.example.content_listing.business.repository.ContentListingRepository
import com.example.content_listing.helper.Constants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetContentListingPagination @Inject constructor(
    private val contentListingRepository: ContentListingRepository,
) {

    operator fun invoke(
        url: String, imageRatio: String
    ): Flow<PagingData<AssetItem>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE)
        ) {
            object : AssetPagingSource() {
                override fun fetchAssetItems(
                    pageNo: Int, pageSize: Int
                ): Flow<Resource<List<AssetItem>?>> {
                    return contentListingRepository.getEntityListing(url, imageRatio)
                }

                override fun loadingDialog(isShow: Boolean) {

                }
            }
        }.flow

    }
}