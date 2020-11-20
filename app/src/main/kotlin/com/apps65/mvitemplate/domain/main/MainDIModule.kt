package com.apps65.mvitemplate.domain.main

import com.apps65.mvitemplate.domain.main.store.MainStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainDIModule {
    @Provides
    @ActivityRetainedScoped
    internal fun provideMainStore(factory: MainStoreFactory) = factory.create()
}
