package com.knightclub.app.business.domain.model.sharing


import com.google.gson.annotations.SerializedName

data class SharingDomainData(
    @SerializedName("sharing_items")
    val sharingDomainItems: List<SharingDomainItem?>?
)