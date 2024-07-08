package com.example.feature_squad.di

import com.example.feature_squad.data.service.SquadService
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
    fun provideListingServiceApi(retrofit: Retrofit): SquadService {
        return retrofit.create(SquadService::class.java)
    }
}