package com.example.standing.data.mapper


import com.example.base.helper.EntityMapper
import com.example.standing.business.domain.model.standing.IPLStandings
import com.example.standing.data.model.standings.StandingEntity
import com.example.standing.data.remote.StandingConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StandingEntityMapper @Inject constructor(
    private val standingConfigContract: StandingConfigContract
) : EntityMapper<StandingEntity, List<IPLStandings?>> {
    override fun toDomain(standingEntity: StandingEntity): List<IPLStandings?> {


        val swapList = standingEntity.teamList?.toMutableList()

        if (standingEntity.isSwapRequired) {

            val swapTeamIndex =
                standingEntity.teamList?.indexOfFirst { it.id?.toInt() == standingEntity.currentTeamId }
                    ?: 0
            if (swapTeamIndex != -1) {
                if (swapTeamIndex > standingEntity.requiredTeamCount ?: 0) {
                    val item =
                        standingEntity.teamList?.getOrNull(swapTeamIndex) ?: return emptyList()

                    swapList?.removeAt(swapTeamIndex)
                    swapList?.add(standingEntity.swapPosition ?: 0, item)
                }
            }
        }

        val updateList = mutableListOf<IPLStandings?>()

        updateList.add(
            IPLStandings(
                isShowForm = standingEntity.isShowForm,
                teamID = 0,
                teamIsQualified = false,
                teamPosition = "Pos",
                title = "Team",
                teamLogoUrl = null,
                scoreList = listOf("MP", "W", "L", "NRR", "PTS"),
                lastMatchesResult = listOf("FORM")
            )
        )

        swapList?.map { entity ->
            val title = if (entity.isQualified == "True") "Q" else entity.pos

            val updatedMatchResult = mutableListOf<String?>()

            val matchResult = entity.matches?.filter { !it.outcome.isNullOrEmpty() }?.takeLast(5)
                ?.map { it.outcome }?.reversed().orEmpty()
            updatedMatchResult.addAll(matchResult)

            val size = matchResult.size

            for (i in 0..4 - size) {
                updatedMatchResult.add("-")
            }
            updateList.add(
                IPLStandings(
                    isShowForm = standingEntity.isShowForm,
                    teamID = entity.id?.toInt(),
                    teamIsQualified = entity.isQualified?.lowercase()?.toBoolean() ?: false,
                    teamPosition = title,
                    title = entity.shortName,
                    teamLogoUrl = standingConfigContract.getTeamLogo(entity.id.orEmpty()),
                    scoreList = listOf(entity.p, entity.w, entity.l, entity.nrr, entity.pts),
                    lastMatchesResult = updatedMatchResult
                )
            )
        }

        return standingEntity.requiredTeamCount?.let {
            updateList.toList().take(it + 1)
        } ?: updateList.toList()
    }
}