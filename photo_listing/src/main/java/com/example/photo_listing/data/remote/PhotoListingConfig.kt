package com.example.feature_fixtures.data.remote

interface PhotoListingConfig {

    fun getPhotosPageListingUrl(): String

    fun getReactionCountUrl(): String

    fun getAppType(): Int

    fun getUserReactionUrl(): String

}