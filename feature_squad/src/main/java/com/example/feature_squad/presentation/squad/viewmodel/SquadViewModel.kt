package com.example.feature_squad.presentation.squad.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.business.interceptor.GetSquadListing
import com.example.feature_squad.data.remote.SquadConfigContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class SquadViewModel @Inject constructor(
    private val getSquadListing: GetSquadListing,
    private val squadConfigContract: SquadConfigContract
) : ViewModel() {

    private val jobs = HashMap<String, Job>()
    private var apiCoroutineScope: CoroutineScope? = null
    private var teamId: String? = null

    private val _playerList = MutableLiveData< List<PlayerItem>>()
    val player: LiveData<List<PlayerItem>>
        get() = _playerList

    init {
        getFixtureList()
    }

    fun getFixtureList(teamId: String? = null) {
        this.teamId = teamId
        apiCoroutineScope = createApiCoroutineScope()
        apiCoroutineScope?.launch {
            val result = getSquadListing().map {
                when (it) {
                    is Resource.Loading -> Resource.Loading()
                    is Resource.Error -> Resource.Error(throwable = it.throwable)
                    else ->
                        if (it.data?.squadList.isNullOrEmpty()) {
                            Resource.Error(
                                throwable = NetworkThrowable(
                                    null,
                                    ""
                                )
                            )
                        } else {
                            Log.d("Squad List", it.data?.squadList.orEmpty().toString())
                            Resource.Success(
                                data = it.data?.squadList.orEmpty(),
                            )
                        }
                }
            }
            _playerList.postValue(result.firstOrNull()?.data?: emptyList())
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

}