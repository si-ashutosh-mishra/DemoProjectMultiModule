package com.example.feature_news.di

import com.example.feature_news.business.repository.ListingRepository
import com.example.feature_news.data.repository.ListingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface NewsRepositoryModule {

    @Binds
    @ViewModelScoped
    fun provideListingRepository(fixturesRepositoryImpl: ListingRepositoryImpl): ListingRepository

}