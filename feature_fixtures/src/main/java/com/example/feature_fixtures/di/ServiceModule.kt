package com.example.feature_fixtures.di

import com.example.feature_fixtures.data.service.FixturesService
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
    fun provideListingServiceApi(retrofit: Retrofit): FixturesService {
        return retrofit.create(FixturesService::class.java)
    }
}