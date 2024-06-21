package com.example.satndings.di

import com.example.satndings.data.service.StandingService
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
    fun provideListingServiceApi(retrofit: Retrofit): StandingService {
        return retrofit.create(StandingService::class.java)
    }
}