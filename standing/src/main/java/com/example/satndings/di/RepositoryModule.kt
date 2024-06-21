package com.example.satndings.di


import com.example.satndings.business.repository.StandingRepository
import com.example.satndings.data.repository.StandingRepositoryImpl
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