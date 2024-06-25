package com.example.standing.presentation.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.base.ui.common.BaseViewModel
import com.example.standing.business.domain.model.standing.IPLStandings
import com.example.standing.business.interactor.GetStandingData
import com.example.standing.data.remote.StandingConfigContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingViewModel @Inject constructor(
    private val getStandingsData: GetStandingData,
    private val standingConfigContract: StandingConfigContract
) : BaseViewModel() {

    private val _standingLiveData = MutableLiveData<List<IPLStandings?>>()
    val standingLiveData: LiveData<List<IPLStandings?>> get() = _standingLiveData


    fun loadStanding(
        isShowForm: Boolean = false,
        isSwapRequired: Boolean = false,
        requiredTeamCount: Int? = null,
        swapPosition: Int? = null,
        currentTeamId: Int? = null,
    ) {
        viewModelScope.launch {
            getStandingsData(
                url = standingConfigContract.getStandingUrl(),
                isShowForm = isShowForm,
                isSwapRequired = isSwapRequired,
                teamCount = requiredTeamCount,
                swapPosition = swapPosition,
                currentTeamId = currentTeamId
            ).collectLatest {
                when (it) {
                    is Resource.Loading -> setLoading(true)
                    is Resource.Error -> setLoading(false)
                    is Resource.Success -> {
                        _standingLiveData.value = it.data.orEmpty()
                    }

                    else -> {
                        setLoading(false)
                    }
                }
            }
        }
    }

}