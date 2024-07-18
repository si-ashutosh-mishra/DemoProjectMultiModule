package com.example.photo_listing.data.remote

interface PhotoDetailsConfig {
    fun getContentSharingUrl(entityCategory: String?,
                             titleAlias: String?) : String
    fun getContentImageUrl1(imagePath: String?,
                            imageName: String?,
                            imageRatio: String? = null) : String
}