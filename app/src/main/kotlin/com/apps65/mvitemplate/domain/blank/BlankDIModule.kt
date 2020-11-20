package com.apps65.mvitemplate.domain.blank

import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object BlankDIModule {
    @Provides
    @ActivityRetainedScoped
    internal fun provideBlankStore(factory: BlankStoreFactory) = factory.create()
}
