package com.example.photo_listing.di

import com.example.photo_listing.data.service.ListingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    @ViewModelScoped
    fun provideListingServiceApi(retrofit: Retrofit) :ListingService{
        return retrofit.create(ListingService::class.java)
    }
}