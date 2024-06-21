package com.example.satndings.presentation.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.base.ui.common.BaseViewModel
import com.example.satndings.business.domain.model.standing.IPLStandings
import com.example.satndings.business.interactor.GetStandingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingViewModel @Inject constructor(
    private val getStandingsData: GetStandingData
) : BaseViewModel() {

    private val _standingLiveData = MutableLiveData<List<IPLStandings?>>()
    val standingLiveData: LiveData<List<IPLStandings?>> get() = _standingLiveData


    fun loadStanding(
        isShowForm: Boolean,
        isSwapRequired: Boolean,
        teamCount: Int? = null,
        swapPosition: Int? = null,
        currentTeamId: Int?
    ) {
        viewModelScope.launch {
            getStandingsData(
                url = "https://www.knightclub.in/cricket/live/json/standing_5157.json",
                isShowForm = isShowForm,
                isSwapRequired = isSwapRequired,
                teamCount = teamCount,
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