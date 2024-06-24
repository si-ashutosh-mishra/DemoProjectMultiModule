package com.example.feature_squad.business.interceptor

import com.example.base.helper.Resource
import com.example.feature_squad.business.domain.model.squad.PlayerComparator
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.business.domain.model.squad.SquadStaff
import com.example.feature_squad.business.domain.model.squad.StaffItem
import com.example.feature_squad.business.repository.SquadRepository
import com.example.feature_squad.data.model.SquadList
import com.example.feature_squad.data.model.Staff
import com.example.feature_squad.data.remote.SquadConfigContract
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ViewModelScoped
class GetSquadListing (
    private val squadRepository: SquadRepository,
    private val configManager: SquadConfigContract,
) {
    operator fun invoke(
        isPlayerNameUpperCase: Boolean = true,
        isSupportStaffRequired: Boolean = false,
        currentTeam: Int = 99,
        seriesId: String? = null,
        teamId: String? = null,
        url: String
    ): Flow<Resource<SquadStaff>> {

        return squadRepository.getSquadsListing(
            url = configManager.getSquadListingUrl(seriesId, teamId)
        ).map {
            when (it) {
                is Resource.Error -> Resource.Error(throwable = it.throwable)
                is Resource.Loading -> Resource.Loading()
                else -> {

                    Resource.Success(
                        data = SquadStaff(
                            squadList = getPlayerItems(
                                isPlayerNameUpperCase,
                                squadList = it.data,
                                currentTeam
                            ),
                            if (isSupportStaffRequired) getStaffItem(
                                it.data?.supportStaff.orEmpty(),
                                currentTeam
                            ) else emptyList()
                        )
                    )
                }
            }

        }
    }

    private fun getPlayerItems(
        isPlayerNameUpperCase: Boolean,
        squadList: SquadList?,
        currentTeam: Int
    ): List<PlayerItem> {
        val players = squadList?.players?.map {
            val firstName = it.playerDetails?.name?.substringBefore(" ")
            val lastName = it.playerDetails?.name?.substringAfter(" ")

            PlayerItem(
                playerId = it.playerDetails?.id,
                skillId = it.playerDetails?.skillId,
                skill = it.playerDetails?.skill,
                firstName = if (isPlayerNameUpperCase) firstName?.uppercase() else firstName
                    ?: "",
                lastName = if (isPlayerNameUpperCase) lastName?.uppercase() else lastName
                    ?: "",
                playerImageUrl = configManager.getPlayerImageUrl(it.playerDetails?.id),
                country = it.playerDetails?.nationality,
                countryImageUrl = configManager.getCountryNationalityIdImageUrl(it.playerDetails?.nationalityId),
                overseasPlayer = it.playerDetails?.nationalityId != configManager.getTeamNationalityId(
                    currentTeam = currentTeam
                ),
                bio = null,
                isCaptain = it.playerDetails?.isCaptain ?: false,
                isViceCaptain = it.playerDetails?.isViceCaptain ?: false,
                overAllStats = it.overAllStats
            )
        }
            .orEmpty()
            .toMutableList()

        val playerOrder = configManager.getSquadPlayerOrder()

        return if (playerOrder.isNotEmpty()) {
            val squadMap: HashMap<String, PlayerItem> = hashMapOf()
            players.forEach { player ->
                player.playerId ?: return@forEach
                squadMap[player.playerId] = player
            }
            val selectedSquadList = mutableListOf<PlayerItem>()
            playerOrder.forEach {
                squadMap[it]?.let { player ->
                    selectedSquadList.add(player)
                    players.remove(player)
                }
            }

            players.forEach {
                selectedSquadList.add(it)
            }

            selectedSquadList
        } else {
            players.sortedWith(PlayerComparator())
        }
    }

    private fun getStaffItem(
        supportStaff: List<Staff>,
        currentTeam: Int
    ): List<StaffItem> {
        val staffs = supportStaff.map {
            val firstName = it.name?.substringBefore(" ")
            val lastName = it.name?.substringAfter(" ")

            StaffItem(
                staffId = it.id,
                firstName = firstName,
                lastName = lastName,
                roleId = it.roleId,
                roleName = it.roleName,
                staffImageUrl = configManager.getStaffImageUrl(it.id),
                countryId = it.nationalityId,
                countryName = it.nationalityName,
                countryImageUrl = configManager.getCountryNationalityIdImageUrl(it.nationalityId),
                overseasPlayer = it.nationalityId != configManager.getTeamNationalityId(currentTeam = currentTeam)
            )
        }.toMutableList()

        val staffOrder = configManager.getSquadStaffOrder()

        if (staffOrder.isNotEmpty()) {
            val staffMap: HashMap<String, StaffItem> = hashMapOf()
            staffs.forEach {
                it.staffId ?: return@forEach
                staffMap[it.staffId] = it
            }

            val selectedStaffList = mutableListOf<StaffItem>()
            staffOrder.forEach {
                staffMap[it]?.let { staff ->
                    selectedStaffList.add(staff)
                    staffs.remove(staff)
                }
            }

            staffs.forEach {
                selectedStaffList.add(it)
            }

            return selectedStaffList
        } else {
            return staffs
        }
    }
}