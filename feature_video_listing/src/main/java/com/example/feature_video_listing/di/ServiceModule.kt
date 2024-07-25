package com.example.feature_video_listing.di

import com.example.feature_video_listing.data.service.VideoDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    @ViewModelScoped
    fun provideListingServiceApi(retrofit: Retrofit): VideoDetailService {
        return retrofit.create(VideoDetailService::class.java)
    }
}