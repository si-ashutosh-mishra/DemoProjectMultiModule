package com.example.feature_news.presentation.newslist.typeone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.base.helper.Resource
import com.example.feature_news.business.domain.model.listing.ListingEntityData
import com.example.feature_news.business.interactor.GetNewsLB
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.lb_content_listing.business.interactor.GetContentListingPagination
import com.example.lb_content_listing.business.interactor.paging.AssetPagingSource
import com.example.lb_content_listing.business.repository.ContentListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class NewsListingViewModel @Inject constructor(
    private val getListingPagination: GetContentListingPagination
): ViewModel(){
    
    lateinit var items: Flow<PagingData<AssetItem>>

    private val _listingEntityData = MutableStateFlow<ListingEntityData?>(null)

    fun fetchData(url: String) {
        viewModelScope.launch {
            _listingEntityData.collectLatest {
                items = getListingPagination(
                    url = url,
                    imageRatio = "16-9"
                ).cachedIn(viewModelScope)
            }
        }
    }
}
