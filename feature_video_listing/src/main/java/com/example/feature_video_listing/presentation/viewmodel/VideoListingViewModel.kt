package com.example.feature_video_listing.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_video_listing.business.domain.VideosListingItem
import com.example.feature_video_listing.business.interceptor.GetVideoListing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class VideoListingViewModel @Inject constructor(
    private val getVideoListing: GetVideoListing
) : ViewModel() {

    private val jobs = HashMap<String, Job>()
    private var apiCoroutineScope: CoroutineScope? = null

    private val _videoListing = MutableLiveData<List<VideosListingItem>>()
    val videoListing : LiveData<List<VideosListingItem>> get()  = _videoListing

    fun fetchVideoListing(){
        viewModelScope.launch {
            getVideoListing("https://stg-kc.sportz.io/apiv3/gettemplatedata?url=tkr-app-videos&is_app=1").collectLatest {
                _videoListing.value = it.data ?: _videoListing.value.orEmpty()
            }
        }
    }

    fun cancelApiCoroutine() {
       // clearJobs()
        apiCoroutineScope?.cancel()
        apiCoroutineScope = null
    }
}