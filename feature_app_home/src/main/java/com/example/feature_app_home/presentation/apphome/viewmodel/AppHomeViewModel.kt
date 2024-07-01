package com.example.feature_app_home.presentation.apphome.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.ui.common.BaseViewModel
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.feature_app_home.business.interactor.GetAppHomeListing
import com.example.feature_app_home.data.remote.AppHomeConfigContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppHomeViewModel @Inject constructor(
    private val getAppHomeListing: GetAppHomeListing,
    val appHomeConfigContract: AppHomeConfigContract,
) : BaseViewModel() {

    private val coroutineExceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, throwable ->
            handleException(throwable)
        }
    }


    private var apiCoroutineScope: CoroutineScope? = null

    private val _homeListingItems = MutableLiveData<List<HomeListingItem>>()
    val homeListingItems: LiveData<List<HomeListingItem>>
        get() = _homeListingItems


    fun fetchHomeListing(forceFetch: Boolean) {
        cancelApiCoroutine()
        apiCoroutineScope = createApiCoroutineScope()
        apiCoroutineScope?.launch {
            getAppHomeListing(
                url = appHomeConfigContract.getAppHomeUrl(), forceRefresh = forceFetch
            ).collectLatest {
                _homeListingItems.postValue(it.data ?: _homeListingItems.value.orEmpty())
            }
        }
    }

    private fun cancelApiCoroutine() {
        apiCoroutineScope?.cancel()
        apiCoroutineScope = null
    }

    private fun createApiCoroutineScope() =
        CoroutineScope(SupervisorJob() + coroutineExceptionHandler)

    private fun handleException(exception: Throwable) {
        Log.e(this.javaClass.simpleName, exception.message, exception)
    }

    override fun onCleared() {
        apiCoroutineScope?.cancel()
        super.onCleared()
    }

}