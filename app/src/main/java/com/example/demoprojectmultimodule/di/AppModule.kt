package com.example.demoprojectmultimodule.di


import com.example.base.helper.BaseConfigContract
import com.example.demoprojectmultimodule.data.data.ConfigManager
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

//    @Binds
//    @Singleton
//    fun provideBaseRepository(baseInfoImpl: BaseInfoImpl): BaseInfo

    @Binds
    @Singleton
    fun provideBaseConfig(configManager: ConfigManager): BaseConfigContract

    @Binds
    @Singleton
    fun provideFixtureConfig(configManager: ConfigManager): FixtureConfigContract

}