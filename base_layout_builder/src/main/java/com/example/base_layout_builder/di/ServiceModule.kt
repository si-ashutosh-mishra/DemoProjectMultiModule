package com.example.base_layout_builder.di


import com.example.base_layout_builder.data.service.LBService
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
    fun provideLBServiceApi(retrofit: Retrofit): LBService {
        return retrofit.create(LBService::class.java)
    }
}