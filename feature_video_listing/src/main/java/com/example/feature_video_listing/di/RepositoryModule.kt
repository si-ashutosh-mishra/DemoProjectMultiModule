package com.example.feature_video_listing.di

import com.example.feature_video_listing.business.repository.VideoDetailRepository
import com.example.feature_video_listing.data.repository.VideoDetailImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun providePhotoRepository(photoDetailsImpl: VideoDetailImpl) : VideoDetailRepository
}