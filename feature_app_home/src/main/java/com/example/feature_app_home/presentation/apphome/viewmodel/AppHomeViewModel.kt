package com.example.feature_app_home.presentation.apphome.viewmodel

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.ui.common.BaseViewModel
import com.example.base.utils.CalendarUtils
import com.example.feature_app_home.business.domain.model.home.HomeItemViewType
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.feature_app_home.business.interactor.GetAppHomeListing
import com.example.feature_app_home.data.remote.AppHomeConfigContract
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.presentation.fixture.utils.startCoroutineTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
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

    private val jobs = HashMap<String, Job>()

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

                (it.data?.find { it.type == HomeItemViewType.HOME_FIXTURES } as? HomeListingItem.HomeFixtures)?.let {
                    //scheduleRefreshEventUntilMatchGoLive(it.matches)
                }

            }
        }
    }

    fun cancelApiCoroutine() {
        clearJobs()
        apiCoroutineScope?.cancel()
        apiCoroutineScope = null
    }

    private fun createApiCoroutineScope() =
        CoroutineScope(SupervisorJob() + coroutineExceptionHandler)

    private fun handleException(exception: Throwable) {
        Log.e(this.javaClass.simpleName, exception.message, exception)
    }

    override fun onCleared() {
        cancelApiCoroutine()
        super.onCleared()
    }

    private fun scheduleRefreshEventUntilMatchGoLive(matches: List<IPLMatch?>) {

        apiCoroutineScope?.launch {

            matches.let { matches ->

                matches.forEach { match ->
                    val timeInFuture = CalendarUtils.convertDateStringToMillis(
                        dateString = match?.startDate, dateFormat = "yyyy-MM-dd'T'HH:mmZZZZZ"
                    )

                    when (match?.eventState) {
                        EventState.LIVE -> {
                            jobs[match.gameId.toString()] =
                                startCoroutineTimer(coroutineScope = this,
                                    delayMillis = appHomeConfigContract.getFixturesPollingInterval(),
                                    repeatMillis = appHomeConfigContract.getFixturesPollingInterval(),
                                    action = {
                                        fetchHomeListing(false)
                                    })
                        }

                        EventState.UPCOMING -> {
                            val delayMillis = if (DateUtils.isToday(timeInFuture)) {
                                appHomeConfigContract.getFixturesPollingInterval()
                            } else {
                                (timeInFuture - System.currentTimeMillis()).let {
                                    val updatedTime = it - 1800000
                                    if (updatedTime < 0) appHomeConfigContract.getFixturesPollingInterval()
                                    else updatedTime
                                }
                            }

                            jobs[match.gameId.toString()] =
                                startCoroutineTimer(coroutineScope = this,
                                    delayMillis = delayMillis,
                                    repeatMillis = appHomeConfigContract.getFixturesPollingInterval(),
                                    action = {
                                        fetchHomeListing(false)
                                    })

                        }

                        else -> {
                            jobs[match?.gameId]?.cancel()
                            jobs.remove(match?.gameId)
                        }
                    }
                }
            }
        }
    }

    private fun clearJobs() {
        jobs.clear()
    }

}