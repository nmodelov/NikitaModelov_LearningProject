package com.apps65.mvitemplate.domain

import com.apps65.mvi.store.UnicastStoreFactory
import com.apps65.mvitemplate.domain.blank.BlankDIModule
import com.apps65.mvitemplate.domain.main.MainDIModule
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DomainDIModule.Declarations::class, BlankDIModule::class, MainDIModule::class])
object DomainDIModule {

    @Provides
    internal fun provideStoreFactory(): StoreFactory = UnicastStoreFactory

    @Module
    internal interface Declarations {
        // TBD
    }
}
