package com.example.feature_fixtures.presentation.fixture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.helper.Resource
import com.example.feature_fixtures.business.interceptor.GetListOfMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val getListOfMatches: GetListOfMatches
) : ViewModel() {

    fun getFixtureList() {
        viewModelScope.launch {

            val result = getListOfMatches(
                2,
                "default.aspx?methodtype=3&client=40&sport=1&league=0&timezone=+0530&language=en&tournament=4554",
                ""
            ).firstOrNull { it !is Resource.Loading }?.data

        }
    }
}