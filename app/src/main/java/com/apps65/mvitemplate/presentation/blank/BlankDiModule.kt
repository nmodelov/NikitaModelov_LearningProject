package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.ViewModelFactory
import com.apps65.mvitemplate.presentation.di.FragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
object BlankDiModule {
    @Provides
    @JvmStatic
    @FragmentScope
    fun provideVMFactory(provider: Provider<BlankBinder>): ViewModelFactory<BlankBinder> {
        return ViewModelFactory(provider)
    }
}
