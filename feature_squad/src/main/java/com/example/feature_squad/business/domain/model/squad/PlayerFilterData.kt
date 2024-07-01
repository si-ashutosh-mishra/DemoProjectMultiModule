package com.example.feature_squad.business.domain.model.squad

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerFilterData(
    val title: String,
    val skillId: String,
    val playersList: List<PlayerItem>
):Parcelable