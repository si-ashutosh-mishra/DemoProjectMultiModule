package com.example.feature_fixtures.presentation.fixture.viewmodel

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.helper.Resource
import com.example.base.utils.CalendarUtils
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.business.interceptor.GetListOfMatches
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.feature_fixtures.presentation.fixture.utils.FixturesType
import com.example.feature_fixtures.presentation.fixture.utils.startCoroutineTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val getListOfMatches: GetListOfMatches,
    private val fixtureConfigContract: FixtureConfigContract
) : ViewModel() {

    private val jobs = HashMap<String, Job>()
    private var apiCoroutineScope: CoroutineScope? = null
    private var teamId: String? = null

    private val _fixture = MutableLiveData<List<IPLMatch?>>()
    val fixture: LiveData<List<IPLMatch?>>
        get() = _fixture

    //default.aspx?methodtype=3&client=3727564696&sport=1&league=0&timezone=0530&language=en&tournament=4554
    //default.aspx?methodtype=3&client=7756e60237&sport=1&league=0&timezone=0530&language=0&tournament=4848
    fun getFixtureList(teamId: String? = null, isLoadingRequired: Boolean = false) {
        this.teamId = teamId
        apiCoroutineScope = createApiCoroutineScope()
        apiCoroutineScope?.launch {
            getListOfMatches(
                FixturesType.LISTING.id,
                fixtureConfigContract.getFixturesUrl(),
                teamId,
                itemCount = 0
            ).collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        if (isLoadingRequired) {
                            //loading true
                        }
                    }

                    is Resource.Success -> {
                        //loading false
                        scheduleRefreshEventUntilMatchGoLive(it.data?.allListOfMatches.orEmpty())
                        _fixture.postValue(it.data?.allListOfMatches.orEmpty())
                    }

                    is Resource.Error -> {
                        //loading false
                    }
                }
            }
        }
    }

    private fun createApiCoroutineScope() =
        CoroutineScope(SupervisorJob() + coroutineExceptionHandler)

    private val coroutineExceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { context, throwable ->
            handleException(context, throwable)
        }
    }

    private fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.e(this.javaClass.simpleName, exception.message, exception)
    }

    fun cancelApiCoroutine() {
        clearJobs()
        apiCoroutineScope?.cancel()
        apiCoroutineScope = null
    }

    override fun onCleared() {
        cancelApiCoroutine()
        super.onCleared()
        println("viewModel cleared:::->")
    }

    private fun clearJobs() {
        jobs.clear()
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
                                    delayMillis = fixtureConfigContract.getTimeInterval(),
                                    repeatMillis = fixtureConfigContract.getTimeInterval(),
                                    action = {
                                        cancelApiCoroutine()
                                        getFixtureList(teamId)
                                    })
                        }
                        EventState.UPCOMING -> {
                            val delayMillis = if (DateUtils.isToday(timeInFuture)) {
                                fixtureConfigContract.getTimeInterval()
                            } else {
                                (timeInFuture - System.currentTimeMillis()).let {
                                    val updatedTime = it - 1800000
                                    if (updatedTime < 0) fixtureConfigContract.getTimeInterval()
                                    else updatedTime
                                }
                            }

                            jobs[match.gameId.toString()] =
                                startCoroutineTimer(coroutineScope = this,
                                    delayMillis = delayMillis,
                                    repeatMillis = fixtureConfigContract.getTimeInterval(),
                                    action = {
                                        cancelApiCoroutine()
                                        getFixtureList(teamId)
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
}