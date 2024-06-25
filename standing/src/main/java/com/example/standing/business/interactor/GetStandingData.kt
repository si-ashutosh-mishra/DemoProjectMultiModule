package com.example.standing.business.interactor

import com.example.base.helper.Resource
import com.example.standing.business.domain.model.standing.IPLStandings
import com.example.standing.business.repository.StandingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetStandingData @Inject constructor(
    private val standingRepository: StandingRepository
) {
    operator fun invoke(
        url: String,
        isShowForm: Boolean = false,
        isSwapRequired: Boolean = false,
        teamCount: Int? = null,
        swapPosition: Int? = null,
        currentTeamId: Int? = null
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