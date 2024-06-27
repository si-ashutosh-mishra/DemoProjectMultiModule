package com.example.photo_listing.di

import com.example.photo_listing.business.repository.GetReactionRepository
import com.example.photo_listing.business.repository.PhotosRepository
import com.example.photo_listing.data.repository.GetReactionRepositoryImpl
import com.example.photo_listing.data.repository.PhotoListingRepositoryImpl
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
    fun provideListingRepository(photoListingRepositoryImpl: PhotoListingRepositoryImpl) : PhotosRepository

    @Binds
    @ViewModelScoped
    fun getReactionRepository(getReactionRepositoryImpl: GetReactionRepositoryImpl) : GetReactionRepository
}