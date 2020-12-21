package com.apps65.mvitemplate.presentation.di

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvi.saving.SavedStateKeeperImpl
import com.apps65.mvi.saving.StateBundleKeeper
import com.apps65.mvitemplate.presentation.navigation.AppFlowRouterFactory
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.github.aradxxx.ciceroneflow.FlowCicerone
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationDIModule {
    @Provides
    @Singleton
    internal fun provideStateKeeperImpl() = SavedStateKeeperImpl()

    @Provides
    @Singleton
    internal fun provideFlowRouterFactory(): AppFlowRouterFactory = AppFlowRouterFactory {
        AppRouter()
    }

    @Provides
    @Singleton
    internal fun provideCicerone(appFlowRouterFactory: AppFlowRouterFactory): FlowCicerone<AppRouter> =
        FlowCicerone(appFlowRouterFactory)

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface Declarations {
        @Binds
        fun bindStateKeeper(impl: SavedStateKeeperImpl): SavedStateKeeper

        @Binds
        fun bindStateBundleKeeper(impl: SavedStateKeeperImpl): StateBundleKeeper
    }
}
