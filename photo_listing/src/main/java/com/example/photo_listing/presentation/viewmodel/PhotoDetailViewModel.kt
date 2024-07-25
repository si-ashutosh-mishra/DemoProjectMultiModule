package com.example.photo_listing.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.base.ui.common.BaseViewModel
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.business.interceptor.GetPhotoListingDetails
import com.example.photo_listing.business.model.PhotoDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoListingDetails: GetPhotoListingDetails
): BaseViewModel() {

    private val jobs = HashMap<String, Job>()
    private var apiCoroutineScope: CoroutineScope? = null

    private val _photoDetails = MutableLiveData<PhotoDetails?>()
    val photoDetails get() = _photoDetails

    private var _titleAlias: String ?= null
    fun fetchPhotoDetails(titleAlias : String){
        _titleAlias = titleAlias
        viewModelScope.launch {
            getPhotoListingDetails(titleAlias).collectLatest {
                when(it){
                    is Resource.Loading -> {
                        setLoading(true)
                    }
                    is Resource.Error->{
                        setLoading(false)
                    }
                    is Resource.Success->{
                        _photoDetails.value = it.data
                    }else->setLoading(false)
                }
            }
        }
    }

    private val _moreVideoList: MutableLiveData<List<AssetItem>?> by lazy {
        MutableLiveData()
    }

    fun moreVideoList(){
        viewModelScope.launch {
            getPhotoListingDetails.getMoreVideos().collectLatest {
                when(it){
                    is Resource.Loading->{}
                    is Resource.Error->{}
                    is Resource.Success->{
                        _moreVideoList.value = it.data.orEmpty()
                    }
                    else -> setLoading(false)
                }
            }
        }
    }

    val moreVideosList: LiveData<List<AssetItem>?> by lazy {
        MediatorLiveData<List<AssetItem>?>().apply {
            addSource(_moreVideoList) {
                value = it?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }

            addSource(_photoDetails) {
                value =
                    _moreVideoList.value?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }
        }
    }

    private val _morePhotosList: MutableLiveData<List<AssetItem>?> by lazy {
        MutableLiveData()
    }

     fun morePhotoList(){
        viewModelScope.launch {
            getPhotoListingDetails.getMorePhotos().collectLatest {
                when(it){
                    is Resource.Loading->{}
                    is Resource.Error->{}
                    is Resource.Success->{
                        setLoading(false)
                        _morePhotosList.postValue(it.data.orEmpty())
                    }
                    else -> setLoading(false)
                }
            }
        }
    }

    val morePhotoList: LiveData<List<AssetItem>?> by lazy {
        MediatorLiveData<List<AssetItem>?>().apply {
            addSource(_morePhotosList) {
                value = it?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }

            addSource(_photoDetails) {
                value =
                    _morePhotosList.value?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }
        }
    }


    private val _moreNewsList: MutableLiveData<List<AssetItem>?> by lazy {
        MutableLiveData()
    }

    fun moreNewsList(){
        viewModelScope.launch {
            getPhotoListingDetails.getMorePhotos().collectLatest {
                when(it){
                    is Resource.Loading->{}
                    is Resource.Error->{}
                    is Resource.Success->{
                        _moreNewsList.value = it.data.orEmpty()
                    }
                    else -> setLoading(false)
                }
            }
        }
    }

    val moreNewsList: LiveData<List<AssetItem>?> by lazy {
        MediatorLiveData<List<AssetItem>?>().apply {
            addSource(_moreNewsList) {
                value = it?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }

            addSource(_photoDetails) {
                value =
                    _moreNewsList.value?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }
        }
    }




    fun cancelApiCoroutine() {
        // clearJobs()
        apiCoroutineScope?.cancel()
        apiCoroutineScope = null
    }
}