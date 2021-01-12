package com.apps65.mvitemplate.presentation.di

import android.app.Activity
import androidx.savedstate.SavedStateRegistryOwner
import com.apps65.mvi.saving.SavedStateKeeper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityKeeper

@Module
@InstallIn(ActivityComponent::class)
object ActivityStateKeeperDIModule {
    @Provides
    @ActivityScoped
    @ActivityKeeper
    internal fun provideSavedStateKeeper(activity: Activity): SavedStateKeeper =
        CommonSavedStateKeeper(activity as SavedStateRegistryOwner)
}
