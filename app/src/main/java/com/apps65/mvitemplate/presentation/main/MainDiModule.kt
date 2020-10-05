package com.apps65.mvitemplate.presentation.main

import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.presentation.di.ActivityScope
import com.apps65.mvitemplate.presentation.navigation.AppNavigator
import dagger.Module
import dagger.Provides

@Module
object MainDiModule {
    @Provides
    @ActivityScope
    fun provideNavigator(activity: MainActivity): AppNavigator =
        AppNavigator(activity, R.id.content)
}
