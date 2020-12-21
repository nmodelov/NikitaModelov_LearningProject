package com.apps65.mvitemplate.presentation.subnavigation

import com.apps65.mvi.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Provider

@Module
@InstallIn(FragmentComponent::class)
object SubNavDiModule {
    @Provides
    @FragmentScoped
    fun provideVMFactory(provider: Provider<SubNavBinder>): ViewModelFactory<SubNavBinder> =
        ViewModelFactory(provider)
}
