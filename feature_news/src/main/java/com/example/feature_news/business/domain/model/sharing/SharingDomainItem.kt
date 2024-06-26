package com.knightclub.app.business.domain.model.sharing


import com.google.gson.annotations.SerializedName

data class SharingDomainItem(
    @SerializedName("domain_value")
    val domainValue: String?,
    @SerializedName("id")
    val id: Int?
)