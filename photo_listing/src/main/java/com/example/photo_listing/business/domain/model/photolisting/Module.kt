package com.example.photo_listing.business.domain.model.photolisting

import com.google.gson.annotations.SerializedName

data class Module(
    @SerializedName("component_id")
    val componentId: Int?,
    @SerializedName("display_title")
    val displayTitle: String?,
    @SerializedName("exclude_entities")
    val excludeEntities: List<Int>?,
    @SerializedName("fetch_from")
    val fetchFrom: Int?,
    @SerializedName("meta_info")
    val metaInfo: MetaInfo?,
    @SerializedName("other_entities")
    val otherEntities: List<Int>?,
    @SerializedName("parent_tcmap_id")
    val parentTcmapId: Any?,
    @SerializedName("required_entities")
    val requiredEntities: List<Int>?,
    @SerializedName("selector")
    val selector: String?,
    @SerializedName("show_in_app")
    val showInApp: Int?,
    @SerializedName("show_in_mobile")
    val showInMobile: Int?,
    @SerializedName("show_in_web")
    val showInWeb: Int?,
    @SerializedName("template_component_map_id")
    val templateComponentMapId: Int?,
    @SerializedName("template_id")
    val templateId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("widget_data")
    val widgetData: WidgetData?
)