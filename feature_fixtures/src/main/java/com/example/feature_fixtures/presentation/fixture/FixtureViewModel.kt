package com.example.feature_fixtures.presentation.fixture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.business.interceptor.GetListOfMatches
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val getListOfMatches: GetListOfMatches,
    private val fixtureConfigContract: FixtureConfigContract
) : ViewModel() {


    private val _fixture = MutableLiveData<List<IPLMatch?>>()
    val fixture: LiveData<List<IPLMatch?>>
        get() = _fixture

    //default.aspx?methodtype=3&client=3727564696&sport=1&league=0&timezone=0530&language=en&tournament=4554
    //default.aspx?methodtype=3&client=7756e60237&sport=1&league=0&timezone=0530&language=0&tournament=4848
    fun getFixtureList(teamId:String?=null) {
        viewModelScope.launch {
            val result = getListOfMatches(
                FixturesType.LISTING.id,
                fixtureConfigContract.getFixturesUrl(),
                teamId,
                itemCount = 5
            ).firstOrNull { it !is Resource.Loading }?.data

            _fixture.value = result?.allListOfMatches.orEmpty()
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("viewModel cleared:::->")
    }
}