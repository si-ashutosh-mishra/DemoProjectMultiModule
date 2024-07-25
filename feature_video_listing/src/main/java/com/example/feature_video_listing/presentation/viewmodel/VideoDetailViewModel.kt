package com.example.feature_video_listing.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.base.ui.common.BaseViewModel
import com.example.feature_video_listing.business.interceptor.GetVideoDetails
import com.example.feature_video_listing.business.model.VideoDetails
import com.example.lb_content_listing.business.domain.model.AssetItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class VideoDetailViewModel @Inject constructor(
    private val getVideoDetails: GetVideoDetails
) : BaseViewModel() {

    private val jobs = HashMap<String, Job>()
    private var apiCoroutineScope: CoroutineScope? = null

    private val _videoDetails = MutableLiveData<VideoDetails?>()
    val videoDetails get() = _videoDetails

    private var _titleAlias: String ?= null

    private val _moreVideoList: MutableLiveData<List<AssetItem>?> by lazy {
        MutableLiveData()
    }

    fun fetchVideo(title : String){
        _titleAlias = title
        viewModelScope.launch {
            getVideoDetails(title).collectLatest {
                when(it){
                    is Resource.Loading->{
                        setLoading(true)
                    }
                    is Resource.Error->{
                        setLoading(false)
                    }
                    is Resource.Success->{
                        _videoDetails.value = it.data
                    }else->setLoading(false)
                }
            }
        }
    }

    fun moreVideoList(){
        viewModelScope.launch {
            getVideoDetails.getMoreVideos().collectLatest {
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

            addSource(_videoDetails) {
                value =
                    _moreVideoList.value?.filter { assetItem -> assetItem.titleAlias != _titleAlias }
            }
        }
    }

}