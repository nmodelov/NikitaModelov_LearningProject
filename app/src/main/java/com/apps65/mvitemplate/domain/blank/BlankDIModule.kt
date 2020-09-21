package com.apps65.mvitemplate.domain.blank

import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import com.apps65.mvitemplate.domain.common.DispatchersProvider
import com.apps65.mvitemplate.domain.common.DispatchersProviderImpl
import dagger.Module
import dagger.Provides

@Module
object BlankDIModule {
    @Provides
    internal fun provideBlankStore(factory: BlankStoreFactory) = factory.create()

    @Provides
    internal fun provideDispatchers(): DispatchersProvider = DispatchersProviderImpl
}
