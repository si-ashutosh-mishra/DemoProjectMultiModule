package com.example.demoprojectmultimodule.di


import com.example.base.helper.BaseInfo
import com.example.demoprojectmultimodule.BaseInfoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BaseModule {

    @Binds
    @Singleton
    fun provideBaseRepository(baseInfoImpl: BaseInfoImpl): BaseInfo


}