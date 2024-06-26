package com.example.feature_news.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.feature_news.business.repository.ListingRepository
import com.knightclub.app.business.domain.model.news.NewsListingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val listingRepository: ListingRepository,
): ViewModel(){

    private val _items = MutableLiveData<List<NewsListingItem>>()
    val items: LiveData<List<NewsListingItem>>
        get() = _items

    fun fetchData() {
        viewModelScope.launch {
            listingRepository.getNewsPageListing().collectLatest {
                //setLoading(value = it is Resource.Loading)
                _items.value = it.data ?: _items.value.orEmpty()
            }
        }
    }
}