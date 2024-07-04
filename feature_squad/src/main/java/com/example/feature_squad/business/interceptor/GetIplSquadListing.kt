package com.example.feature_squad.business.interceptor

import androidx.core.text.HtmlCompat
import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.feature_squad.business.domain.model.squad.PlayerComparator
import com.example.feature_squad.business.domain.model.squad.PlayerItem
import com.example.feature_squad.business.domain.model.squad.SquadStaff
import com.example.feature_squad.business.domain.model.squad.StaffItem
import com.example.feature_squad.business.repository.SquadRepository
import com.example.feature_squad.data.model.CustomSquadInfo
import com.example.feature_squad.data.model.SquadList
import com.example.feature_squad.data.model.Staff
import com.example.feature_squad.data.remote.SquadConfigContract
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class GetIplSquadListing @Inject constructor(
    private val squadRepository: SquadRepository,
    private val squadConfig: SquadConfigContract,
) {
    operator fun invoke(
        isPlayerNameUpperCase: Boolean = true,
        isSupportStaffRequired: Boolean = true,
        currentTeam: Int = 99,
        seriesId: String? = null,
        teamId: String? = null,
    ): Flow<Resource<SquadStaff>> {

        return combine(
            squadRepository.getSquadsListing(url = squadConfig.getSquadListingUrl(seriesId, teamId)),
            squadRepository.getSquadCustomFeed(url = squadConfig.getSquadCustomFeedUrl())
        ) { squadListResource, customSquadInfoResource ->
            if (squadListResource is Resource.Loading || customSquadInfoResource is Resource.Loading)
                return@combine Resource.Loading()

            if (squadListResource is Resource.Error) {
                return@combine Resource.Error(throwable = NetworkThrowable(code = null, message = ""))
            }

            if (squadListResource is Resource.Success)
                return@combine Resource.Success(
                    data = SquadStaff(
                        squadList = getPlayerItems(
                            isPlayerNameUpperCase,
                            squadList = squadListResource.data,
                            currentTeam,
                            customSquadInfo = customSquadInfoResource.data
                        ),
                        if (isSupportStaffRequired) getStaffItem(
                            squadListResource.data?.supportStaff.orEmpty(),
                            currentTeam
                        ) else emptyList()
                    )
                )
            return@combine Resource.Loading()
        }
    }

    private fun getPlayerItems(
        isPlayerNameUpperCase: Boolean,
        squadList: SquadList?,
        currentTeam: Int,
        customSquadInfo: CustomSquadInfo?
    ): List<PlayerItem> {
        val players = squadList?.players?.map {
            val playerInfo = it.playerDetails?.id?.let { id ->
                try {
                    customSquadInfo?.players?.find { it.id?.toString() == id }
                } catch (e: Exception) {
                    null
                }
            }

            val skill = it.playerDetails?.skill?.let { skill ->
                if (it.playerDetails.isCaptain == true) {
                    "Captain / $skill"
                } else {
                    skill
                }
            }

            if (playerInfo == null) {
                val firstName = it.playerDetails?.name?.substringBefore(" ")
                val lastName = it.playerDetails?.name?.substringAfter(" ")

                val skill = it.playerDetails?.skill?.let { skill ->
                    if (it.playerDetails.isCaptain == true) {
                        "Captain / $skill"
                    } else {
                        skill
                    }
                }

                PlayerItem(
                    playerId = it.playerDetails?.id,
                    skillId = it.playerDetails?.skillId,
                    skill = skill,
                    firstName = if (isPlayerNameUpperCase) firstName?.uppercase() else firstName
                        ?: "",
                    lastName = if (isPlayerNameUpperCase) lastName?.uppercase() else lastName
                        ?: "",
                    playerImageUrl = squadConfig.getPlayerImageUrl(playerId = it.playerDetails?.id),
                    country = it.playerDetails?.nationality,
                    countryImageUrl = squadConfig.getCountryNationalityIdImageUrl(it.playerDetails?.nationalityId?:""),
                    overseasPlayer = it.playerDetails?.nationalityId != "4",
                    bio = null,
                    isCaptain = it.playerDetails?.isCaptain ?: false,
                    isViceCaptain = it.playerDetails?.isViceCaptain ?: false,
                    overAllStats = it.overAllStats
                )
            }
            else {
                val firstName = playerInfo.name?.substringBefore(" ")
                val lastName = playerInfo.name?.substringAfter(" ")

                PlayerItem(
                    playerId = it.playerDetails.id,
                    skillId = playerInfo.skill?.id.toString(),
                    skill = skill,
                    firstName = if (isPlayerNameUpperCase) firstName?.uppercase() else firstName ?: "",
                    lastName = if (isPlayerNameUpperCase) lastName?.uppercase() else lastName ?: "",
                    playerImageUrl = squadConfig.getPlayerImageUrl(it.playerDetails.id),
                    country = it.playerDetails.nationality,
                    countryImageUrl = squadConfig.getCountryNationalityIdImageUrl(it.playerDetails.nationalityId ?: ""),
                    overseasPlayer = it.playerDetails.nationalityId != "4",
                    bio = playerInfo.bio?.let { bio ->
                        HtmlCompat.fromHtml(
                            bio,
                            HtmlCompat.FROM_HTML_MODE_LEGACY
                        ).toString()
                    },
                    isCaptain = it.playerDetails.isCaptain ?: false,
                    isViceCaptain = it.playerDetails.isViceCaptain ?: false,
                    overAllStats = it.overAllStats
                )
            }

        }.orEmpty().toMutableList()

        val playerOrder = squadConfig.getSquadPlayerOrder()

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
                staffImageUrl = squadConfig.getStaffImageUrl(it.id),
                countryId = it.nationalityId,
                countryName = it.nationalityName,
                countryImageUrl = squadConfig.getCountryNationalityIdImageUrl(it.nationalityId),
                overseasPlayer = it.nationalityId != squadConfig.getTeamNationalityId(currentTeam = currentTeam)
            )
        }.toMutableList()

        val staffOrder = squadConfig.getSquadStaffOrder()

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