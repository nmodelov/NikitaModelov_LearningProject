package com.apps65.mvitemplate.domain.blank

import com.apps65.mvitemplate.domain.blank.store.BlankExecutorDelegate
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import com.apps65.mvitemplate.domain.blank.store.RollDiceExecutorDelegate
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.multibindings.IntoSet

@Module
@InstallIn(BlankComponent::class)
object BlankDIModule {
    @Provides
    @FeatureScope
    internal fun provideBlankStore(factory: BlankStoreFactory) = factory.create()

    @Module
    @InstallIn(BlankComponent::class)
    internal interface BlankDIExModule {
        @Binds
        @FeatureScope
        @IntoSet
        fun provideRollDiceExecutorDelegate(delegate: RollDiceExecutorDelegate): BlankExecutorDelegate
    }
}
