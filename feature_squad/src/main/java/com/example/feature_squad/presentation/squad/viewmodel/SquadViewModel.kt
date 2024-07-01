package com.example.feature_squad.presentation.squad.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.feature_squad.business.domain.model.squad.PlayerComparator
import com.example.feature_squad.business.domain.model.squad.PlayerFilterData
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.business.domain.model.squad.SquadModel
import com.example.feature_squad.business.domain.model.squad.StaffItem
import com.example.feature_squad.business.interceptor.GetSquadListing
import com.example.feature_squad.data.remote.SquadConfigContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SquadViewModel @Inject constructor(
    private val getSquadListing: GetSquadListing,
    private val squadConfigContract: SquadConfigContract
) : ViewModel() {

    private var teamId: String? = null

    private val _playerList = MutableLiveData<List<PlayerItem>>()
    val player: LiveData<List<PlayerItem>>
        get() = _playerList

    private val _squadStaffListing = MutableLiveData<SquadModel?>()

    val squadStaffListing: LiveData<SquadModel?>
        get() = _squadStaffListing

    init {
        getSquadList()
    }

    fun getSquadList(teamId: String? = null) {
        this.teamId = teamId
        viewModelScope.launch {
            val result = getSquadListing().collectLatest {
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
                            _playerList.postValue(it.data?.squadList.orEmpty())
                            _squadStaffListing.postValue(getFilterPlayerList(it.data?.squadList.orEmpty(), it.data?.staffList.orEmpty()))
                            Log.d("Squad filter", "${getFilterPlayerList(it.data?.squadList.orEmpty(),
                                it.data?.staffList.orEmpty())}")
                            Resource.Success(
                                data = it.data?.squadList.orEmpty(),
                            )
                        }
                }
            }
        }
    }


    private fun getFilterPlayerList(
        squadList: List<PlayerItem>?,
        staffList: List<StaffItem>
    ): SquadModel {

        val filteredPlayersList = mutableListOf<PlayerFilterData>()

        val skillList = squadConfigContract.getSkillList()

        skillList.forEach { skillItem ->

            val list = squadList?.filter { it.skillId == skillItem.skill_id }?.sortedWith(
                PlayerComparator()
            ) ?: emptyList()

            if (list.isNotEmpty()) {
                filteredPlayersList.add(
                    PlayerFilterData(
                        skillItem.skill_name,
                        skillItem.skill_id,
                        list
                    )
                )
            }
        }

        return SquadModel(filteredPlayersList, staffList)
    }

}