package com.apps65.mvitemplate

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initLogging()
        initDI()
    }

    private fun initDI() {
        val appComponent = DaggerAppDIComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            TODO("Plant a tree for production")
        }
    }
}
