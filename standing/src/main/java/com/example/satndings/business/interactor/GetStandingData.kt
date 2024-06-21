package com.example.satndings.business.interactor

import com.example.base.helper.Resource
import com.example.satndings.business.domain.model.standing.IPLStandings
import com.example.satndings.business.repository.StandingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetStandingData @Inject constructor(
    private val standingRepository: StandingRepository
) {
    operator fun invoke(
        url: String,
        isShowForm: Boolean,
        isSwapRequired: Boolean,
        teamCount: Int?,
        swapPosition: Int?,
        currentTeamId: Int?
    ): Flow<Resource<List<IPLStandings?>?>> {
        return standingRepository.getStandingData(
            url = url,
            isShowForm = isShowForm,
            isSwapRequired = isSwapRequired,
            teamCount = teamCount,
            swapPosition = swapPosition,
            currentTeamId = currentTeamId
        )
    }
}