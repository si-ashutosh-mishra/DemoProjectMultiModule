package com.example.standing.di


import com.example.standing.business.repository.StandingRepository
import com.example.standing.data.repository.StandingRepositoryImpl
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
    fun provideStandingRepository(standingRepositoryImpl: StandingRepositoryImpl): StandingRepository

}