package com.apps65.mvitemplate.domain.tabcontainer

import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object TabContainerDIModule {
    @Provides
    @FragmentScoped
    internal fun provideTabContainerStore(factory: TabContainerStoreFactory) = factory.create()
}
