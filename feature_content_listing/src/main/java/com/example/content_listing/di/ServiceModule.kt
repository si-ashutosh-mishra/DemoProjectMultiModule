package com.example.content_listing.di


import com.example.content_listing.data.service.ContentListingService
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
    fun provideContentListingServiceApi(retrofit: Retrofit): ContentListingService {
        return retrofit.create(ContentListingService::class.java)
    }
}