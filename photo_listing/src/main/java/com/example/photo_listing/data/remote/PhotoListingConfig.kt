package com.example.photo_listing.data.remote

interface PhotoListingConfig {

    fun getPhotosPageListingUrl(): String

    fun getCorousalImageUrl(imagePath: String?, imageName: String?, imageRatio: String?) : String

}