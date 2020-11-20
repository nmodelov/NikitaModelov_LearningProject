package com.apps65.mvitemplate.domain

import com.apps65.mvi.store.UnicastStoreFactory
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DomainDIModule {

    @Provides
    @ActivityRetainedScoped
    internal fun provideStoreFactory(): StoreFactory = UnicastStoreFactory
}
