package com.example.feature_squad.data.model

import com.google.gson.annotations.SerializedName

data class Staff(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nationality_id")
    val nationalityId: String?,
    @SerializedName("nationality_name")
    val nationalityName: String?,
    @SerializedName("role_id")
    val roleId: String?,
    @SerializedName("role_name")
    val roleName: String?
)