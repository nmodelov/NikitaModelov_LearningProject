package com.apps65.mvitemplate.domain.main

import com.apps65.mvitemplate.domain.main.store.MainStoreFactory
import dagger.Module
import dagger.Provides

@Module
object MainDIModule {
    @Provides
    internal fun provideMainStore(factory: MainStoreFactory) = factory.create()
}
