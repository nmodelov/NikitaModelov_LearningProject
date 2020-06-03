package com.apps65.mvitemplate.presentation.di

import com.apps65.mvitemplate.presentation.main.MainActivity
import com.apps65.mvitemplate.presentation.main.MainDiModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityDIModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainDiModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}
