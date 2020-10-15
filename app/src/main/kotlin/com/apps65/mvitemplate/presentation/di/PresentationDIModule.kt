package com.apps65.mvitemplate.presentation.di

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvi.saving.SavedStateKeeperImpl
import com.apps65.mvi.saving.StateBundleKeeper
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module(
    includes = [
        AndroidInjectionModule::class,
        ActivityDIModule::class,
        FragmentDIModule::class,
        PresentationDIModule.Declarations::class
    ]
)
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
    internal fun provideNavigatorHolder(cicerone: Cicerone<AppRouter>) = cicerone.navigatorHolder

    @Module
    internal interface Declarations {
        @Binds
        fun bindStateKeeper(impl: SavedStateKeeperImpl): SavedStateKeeper

        @Binds
        fun bindStateBundleKeeper(impl: SavedStateKeeperImpl): StateBundleKeeper
    }
}
