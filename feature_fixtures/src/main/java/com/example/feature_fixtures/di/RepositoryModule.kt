package com.example.feature_fixtures.di

import com.example.feature_fixtures.business.repository.FixtureRepository
import com.example.feature_fixtures.data.repository.FixturesRepositoryImpl
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
    fun provideListingRepository(fixturesRepositoryImpl: FixturesRepositoryImpl): FixtureRepository

}