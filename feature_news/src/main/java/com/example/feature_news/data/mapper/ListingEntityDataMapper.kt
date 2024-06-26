package com.example.feature_news.data.mapper


import com.example.base.helper.EntityMapper
import com.example.content_listing.data.model.layoutbuilder.Module
import com.example.feature_news.business.domain.model.listing.ListingEntityData
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