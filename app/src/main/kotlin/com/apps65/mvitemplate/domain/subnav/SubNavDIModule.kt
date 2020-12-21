package com.apps65.mvitemplate.domain.subnav

import com.apps65.mvitemplate.domain.subnav.store.SubNavStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(SubNavComponent::class)
object SubNavDIModule {
    @Provides
    @FeatureScope
    internal fun provideSubNavStore(factory: SubNavStoreFactory) = factory.create()
}
