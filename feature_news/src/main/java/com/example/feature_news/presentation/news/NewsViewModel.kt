package com.example.feature_news.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_news.business.interactor.GetNewsLB
import com.example.feature_news.business.domain.model.news.NewsListingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    //private val listingRepository: ListingRepository,
    private val getNewsLB: GetNewsLB,
): ViewModel(){

    private val _items = MutableLiveData<List<NewsListingItem>>()
    val items: LiveData<List<NewsListingItem>>
        get() = _items

    fun fetchData(url: String) {
        viewModelScope.launch {
            getNewsLB.invoke(url).collectLatest {
                //setLoading(value = it is Resource.Loading)
                _items.value = it.data ?: _items.value.orEmpty()
            }
        }
    }
}