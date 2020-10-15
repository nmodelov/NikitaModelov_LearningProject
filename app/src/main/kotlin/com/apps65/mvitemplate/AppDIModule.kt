package com.apps65.mvitemplate

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDIModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}
