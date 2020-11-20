package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Provider

@Module
@InstallIn(FragmentComponent::class)
object BlankDiModule {
    @Provides
    @FragmentScoped
    fun provideVMFactory(provider: Provider<BlankBinder>): ViewModelFactory<BlankBinder> =
        ViewModelFactory(provider)
}
