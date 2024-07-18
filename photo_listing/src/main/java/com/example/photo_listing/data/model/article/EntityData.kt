package com.knightclub.app.data.model.details.article


import com.google.gson.annotations.SerializedName

data class EntityData(
    @SerializedName("canonical")
    val canonical: String?,
    @SerializedName("content_count")
    val contentCount: Int?,
    @SerializedName("ent_disp_name")
    val entDispName: String?,
    @SerializedName("entity_role_map_id")
    val entityRoleMapId: Int?,
    @SerializedName("entity_type_id")
    val entityTypeId: Int?,
    @SerializedName("entity_type_name")
    val entityTypeName: String?,
    @SerializedName("entity_url")
    val entityUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("priority")
    val priority: Int?
)