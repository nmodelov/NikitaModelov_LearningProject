package com.apps65.mvitemplate.domain.blank

import com.apps65.mvitemplate.domain.blank.store.BlankExecutorDelegate
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import com.apps65.mvitemplate.domain.blank.store.RollDiceExecutorDelegate
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object BlankDIModule {
    @Provides
    @ActivityRetainedScoped
    internal fun provideBlankStore(factory: BlankStoreFactory) = factory.create()

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    internal interface BlankDIExModule {
        @Binds
        @ActivityRetainedScoped
        @IntoSet
        fun provideRollDiceExecutorDelegate(delegate: RollDiceExecutorDelegate): BlankExecutorDelegate
    }
}
