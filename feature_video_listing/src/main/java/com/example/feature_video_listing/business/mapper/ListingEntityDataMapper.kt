package com.example.feature_video_listing.business.mapper

import com.example.base.helper.EntityMapper
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import com.example.feature_video_listing.business.listing.ListingEntityData
import javax.inject.Inject

class ListingEntityDataMapper @Inject constructor(): EntityMapper<Module, ListingEntityData> {
    override fun toDomain(entity: Module): ListingEntityData {
        return ListingEntityData(
            entities = "" /*entity.requiredEntities?.joinToString(separator = ",").orEmpty()*/,
            otherEntities = "" /*entity.otherEntities?.joinToString(separator = ",").orEmpty()*/,
            excludeEntities = ""/*entity.excludeEntities?.joinToString(separator = ",").orEmpty()*/
        )
    }
}