package com.example.feature_app_home.data.mapper

import com.example.base.helper.EntityMapper
import com.example.feature_app_home.business.domain.model.home.HomeItemViewType
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.lb_content_listing.business.domain.model.Component
import com.example.lb_content_listing.business.domain.model.WidgetView
import com.example.lb_content_listing.data.mapper.AssetItemEntityMapper
import com.example.lb_content_listing.data.model.layoutbuilder.Module
import javax.inject.Inject

class AppHomeModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper,
) : EntityMapper<List<Module>?, List<HomeListingItem>?> {

    override fun toDomain(entity: List<Module>?): List<HomeListingItem> {

        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component, layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {
                HomeItemViewType.HOME_STANDING -> {
                    HomeListingItem.HomeStandingData(
                        widgetTitle = module.displayTitle.orEmpty(),
                        showWidgetTitle = module.metaInfo?.showWidgetTitle == 1,
                        showMore = module.metaInfo?.showWidgetTitle == 1,
                        items = emptyList()
                    )
                }

                HomeItemViewType.HOME_FIXTURES -> HomeListingItem.HomeFixtures(
                    matches = emptyList()
                )

                else -> HomeListingItem.Unknown
            }

        }.orEmpty()

    }

    private fun getWidgetType(componentName: String?, layoutType: String?): HomeItemViewType {
        return when {
            componentName == Component.SI_STANDINGS.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.HOME_STANDING
            componentName == Component.SI_SCORESTRIP.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.HOME_FIXTURES
            else -> HomeItemViewType.UNKNOWN
        }
    }

}