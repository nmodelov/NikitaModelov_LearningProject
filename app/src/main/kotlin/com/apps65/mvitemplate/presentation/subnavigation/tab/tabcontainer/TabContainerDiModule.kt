package com.apps65.mvitemplate.presentation.subnavigation.tab.tabcontainer

import com.apps65.mvi.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Provider

@Module
@InstallIn(FragmentComponent::class)
object TabContainerDiModule {
    @Provides
    @FragmentScoped
    fun provideVMFactory(provider: Provider<TabContainerBinder>): ViewModelFactory<TabContainerBinder> =
        ViewModelFactory(provider)
}
