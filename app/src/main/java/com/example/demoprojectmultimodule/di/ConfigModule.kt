package com.example.demoprojectmultimodule.di


import com.example.base.helper.BaseConfigContract
import com.example.lb_content_listing.data.remote.ContentListingConfigContract
import com.example.demoprojectmultimodule.data.data.ConfigManager
import com.example.feature_app_home.data.remote.AppHomeConfigContract
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import com.example.standing.data.remote.StandingConfigContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ConfigModule {

//    @Binds
//    @Singleton
//    fun provideBaseRepository(baseInfoImpl: BaseInfoImpl): BaseInfo

    @Binds
    @Singleton
    fun provideBaseConfig(configManager: ConfigManager): BaseConfigContract

    @Binds
    @Singleton
    fun provideFixtureConfig(configManager: ConfigManager): FixtureConfigContract

    @Binds
    @Singleton
    fun provideStandingConfig(configManager: ConfigManager): StandingConfigContract

    @Binds
    @Singleton
    fun provideContentListingConfig(configManager: ConfigManager): ContentListingConfigContract

    @Binds
    @Singleton
    fun provideAppHomeConfig(configManager: ConfigManager): AppHomeConfigContract

}