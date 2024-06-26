package com.example.base_layout_builder.di


import com.example.base_layout_builder.business.repository.LBRepositoryImpl
import com.example.base_layout_builder.data.repository.LBRepository
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
    fun provideLBRepository(lbRepositoryImpl: LBRepositoryImpl): LBRepository

}