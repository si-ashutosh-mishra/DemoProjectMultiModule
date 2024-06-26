package com.example.standing.business.repository

import com.example.base.helper.Resource
import com.example.standing.business.domain.model.standing.IPLStandings
import kotlinx.coroutines.flow.Flow

interface StandingRepository {

    fun getStandingData(
        url: String,
        isShowForm: Boolean,
        isSwapRequired: Boolean,
        teamCount: Int?,
        swapPosition: Int?,
        currentTeamId: Int?
    ): Flow<Resource<List<IPLStandings?>?>>

}