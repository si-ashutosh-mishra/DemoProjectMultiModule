package com.example.feature_squad.business.domain.model.squad

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SquadModel(
    val playerFilteredData:List<PlayerFilterData>,
    val listOfStaff:List<StaffItem>
):Parcelable
