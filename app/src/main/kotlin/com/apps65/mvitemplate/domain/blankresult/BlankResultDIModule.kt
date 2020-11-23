package com.apps65.mvitemplate.domain.blankresult

import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(FeatureComponent::class)
object BlankResultDIModule {
    @Provides
    @FeatureScope
    internal fun provideBlankResultStore(factory: BlankResultStoreFactory) = factory.create()
}
