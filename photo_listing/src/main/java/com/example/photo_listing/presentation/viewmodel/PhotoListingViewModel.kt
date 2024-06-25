package com.example.photo_listing.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photo_listing.business.interceptor.GetPhotoListing
import com.example.photo_listing.business.model.PhotoListingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class PhotoListingViewModel @Inject constructor(
    private val getPhotoListing: GetPhotoListing
) : ViewModel() {

    private val jobs = HashMap<String, Job>()
    private var apiCoroutineScope: CoroutineScope? = null

    private val _photoListing = MutableLiveData<List<PhotoListingItem>>()
    val photoListing : LiveData<List<PhotoListingItem>> get()  = _photoListing

    fun fetchPhotoListing(){
        viewModelScope.launch {
            getPhotoListing.invoke("https://www.knightclub.in/apiv4/gettemplatedata?url=lakr-app-photos&is_app=1").collectLatest {
                _photoListing.value = it.data ?: _photoListing.value.orEmpty()
            }
        }
    }

    fun cancelApiCoroutine() {
       // clearJobs()
        apiCoroutineScope?.cancel()
        apiCoroutineScope = null
    }
}