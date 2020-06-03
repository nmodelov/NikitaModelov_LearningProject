package com.apps65.mvitemplate.domain

import com.apps65.mvitemplate.domain.blank.BlankDIModule
import com.apps65.mvitemplate.domain.main.MainDIModule
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DomainDIModule.Declarations::class, BlankDIModule::class, MainDIModule::class])
object DomainDIModule {

    @Provides
    internal fun provideStoreFactory(): StoreFactory = DefaultStoreFactory

    @Module
    internal interface Declarations {
        // TBD
    }
}
