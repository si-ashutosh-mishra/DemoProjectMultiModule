package com.knightclub.app.data.model.layoutbuilder

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.knightclub.app.data.model.loyalty.BannerRedirectionModel

data class AssetItemEntity(

    @SerializedName("rno") val rno: Int,
    @SerializedName("tags") val tags: String?,
    @SerializedName("asset_id") val assetId: Int?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("author_id") val authorId: Int,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("is_active") val isActive: Int,
    @SerializedName("user_name") val userName: String?,
    @SerializedName("asset_guid") val assetGuid: String?,
    @SerializedName("created_by") val createdBy: Int,
    @SerializedName("image_path") val imagePath: String?,
    @SerializedName("is_deleted") val isDeleted: Int,
    @SerializedName("is_trashed") val isTrashed: Int,
    @SerializedName("short_desc") val shortDesc: String?,
    @SerializedName("updated_by") val updatedBy: Int,
    @SerializedName("asset_title") val assetTitle: String?,
    @SerializedName("short_title") val shortTitle: String?,
    @SerializedName("show_in_app") val showInApp: Int,
    @SerializedName("show_in_web") val showInWeb: String?,
    @SerializedName("title_alias") val titleAlias: String?,
    @SerializedName("created_date") val createdDate: String?,
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("is_published") val isPublished: Int,
    @SerializedName("order_number") val orderNumber: String?,
    @SerializedName("total_assets") val totalAssets: String?,
    @SerializedName("updated_date") val updatedDate: String?,
    @SerializedName("asset_list_id") val assetListId: Int,
    @SerializedName("asset_type_id") val assetTypeId: Int,
    @SerializedName("published_date") val publishedDate: String?,
    @SerializedName("show_copyright") val showCopyright: String?,
    @SerializedName("show_in_mobile") val showInMobile: String?,
    @SerializedName("image_file_name") val imageFileName: String?,
    @SerializedName("pri_ent_disp_name") val priEntDispName: String?,
    @SerializedName("sec_ent_disp_name") val secEntDispName: String?,
    @SerializedName("primary_entity_name") val primaryEntityName: String?,
    @SerializedName("secondary_entity_name") val secondaryEntityName: String?,
    @SerializedName("published_version_number") val publishedVersionNumber: Int,
    @SerializedName("primary_entity_role_map_id") val primaryEntityRoleMapId: Int,
    @SerializedName("secondary_entity_role_map_id") val secondaryEntityRoleMapId: Int,
    @SerializedName("pri_ent_url") val priEntUrl: String?,
    @SerializedName("sec_ent_url") val secEntUrl: String?,
    @SerializedName("content_source_id") val contentSourceId: String?,
    @SerializedName("video_url") val videoUrl: String?,

    @SerializedName("description") val description: String?,
    @SerializedName("offer") val offer: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("discount_price") val discountPrice: String?,
    @SerializedName("external_link") val externalLink: String?,
    @SerializedName("info_url") val infoUrl: String?,
    @SerializedName("custom_json") val customJson: String?,
) {
    fun getCustomJson(): BannerRedirectionModel? {
        return if (customJson != null) {
            Gson().fromJson(customJson, BannerRedirectionModel::class.java)
        } else {
            null
        }
    }
}