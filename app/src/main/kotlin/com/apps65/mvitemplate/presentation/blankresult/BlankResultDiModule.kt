package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Provider

@Module
@InstallIn(FragmentComponent::class)
object BlankResultDiModule {
    @Provides
    @FragmentScoped
    fun provideVMFactory(provider: Provider<BlankResultBinder>): ViewModelFactory<BlankResultBinder> =
        ViewModelFactory(provider)
}
