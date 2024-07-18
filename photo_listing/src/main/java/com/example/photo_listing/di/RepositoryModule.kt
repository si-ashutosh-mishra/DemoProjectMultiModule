package com.example.photo_listing.di

import com.example.photo_listing.business.repository.PhotoDetailRepository
import com.example.photo_listing.data.repository.PhotoDetailsImpl
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
    fun providePhotoRepository(photoDetailsImpl: PhotoDetailsImpl) : PhotoDetailRepository
}