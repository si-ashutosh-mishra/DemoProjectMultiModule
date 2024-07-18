package com.example.photo_listing.di

import com.example.photo_listing.data.service.PhotoDetailsService
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
    fun provideListingServiceApi(retrofit: Retrofit): PhotoDetailsService {
        return retrofit.create(PhotoDetailsService::class.java)
    }
}