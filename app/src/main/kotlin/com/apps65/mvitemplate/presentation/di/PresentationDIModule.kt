package com.apps65.mvitemplate.presentation.di

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvi.saving.SavedStateKeeperImpl
import com.apps65.mvi.saving.StateBundleKeeper
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
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
    internal fun provideCicerone(): Cicerone<AppRouter> = Cicerone.create(AppRouter())

    @Provides
    internal fun provideRouter(cicerone: Cicerone<AppRouter>): Router = cicerone.router

    @Provides
    internal fun provideNavigatorHolder(cicerone: Cicerone<AppRouter>) = cicerone.getNavigatorHolder()

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface Declarations {
        @Binds
        fun bindStateKeeper(impl: SavedStateKeeperImpl): SavedStateKeeper

        @Binds
        fun bindStateBundleKeeper(impl: SavedStateKeeperImpl): StateBundleKeeper
    }
}
