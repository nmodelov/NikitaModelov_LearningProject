package com.apps65.mvitemplate.domain.blank

import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import dagger.Module
import dagger.Provides

@Module
object BlankDIModule {
    @Provides
    internal fun provideBlankStore(factory: BlankStoreFactory) = factory.create()
}
