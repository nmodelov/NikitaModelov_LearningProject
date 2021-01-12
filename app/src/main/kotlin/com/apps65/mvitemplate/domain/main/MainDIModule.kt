package com.apps65.mvitemplate.domain.main

import com.apps65.mvitemplate.domain.main.store.MainStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainDIModule {
    @Provides
    @ActivityScoped
    internal fun provideMainStore(factory: MainStoreFactory) = factory.create()
}
