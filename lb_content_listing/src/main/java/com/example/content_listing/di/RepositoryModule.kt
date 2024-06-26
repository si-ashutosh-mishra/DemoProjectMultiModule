package com.example.content_listing.di


import com.example.content_listing.business.repository.ContentListingRepository
import com.example.content_listing.business.repository.LBRepository
import com.example.content_listing.data.repository.ContentListingRepositoryImpl
import com.example.content_listing.data.repository.LBRepositoryImpl
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
    fun provideContentListingRepository(contentListingRepositoryImpl: ContentListingRepositoryImpl): ContentListingRepository

    @Binds
    @ViewModelScoped
    fun provideLBRepository(lbRepositoryImpl: LBRepositoryImpl): LBRepository

}