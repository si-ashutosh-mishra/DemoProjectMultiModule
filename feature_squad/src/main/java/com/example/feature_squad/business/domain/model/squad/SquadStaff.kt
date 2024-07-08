package com.example.feature_squad.business.domain.model.squad

import android.os.Parcelable
import com.example.feature_squad.data.model.OverAllStats
import kotlinx.parcelize.Parcelize


data class SquadStaff(
    val squadList: List<PlayerItem>,
    val staffList: List<StaffItem>,
)
@Parcelize
data class PlayerItem(
    val playerId: String?,
    val firstName: String?,
    val lastName: String?,
    val skillId: String?,
    val skill: String?,
    val playerImageUrl: String?,
    val country: String?,
    val countryImageUrl: String?,
    val overseasPlayer: Boolean,
    val bio: String?,
    val isCaptain: Boolean,
    val isViceCaptain: Boolean,
    val overAllStats: OverAllStats?,
):Parcelable

@Parcelize
data class StaffItem(
    val staffId: String?,
    val firstName: String?,
    val lastName: String?,
    val roleId: String?,
    val roleName: String?,
    val staffImageUrl: String?,
    val countryId: String?,
    val countryName: String?,
    val countryImageUrl: String?,
    val overseasPlayer: Boolean
): Parcelable

@Parcelize
data class SkillItem(
    val skill_id: String,
    val skill_name: String
): Parcelable

