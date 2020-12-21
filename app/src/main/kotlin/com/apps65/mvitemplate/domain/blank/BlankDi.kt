package com.apps65.mvitemplate.domain.blank

import com.apps65.mvitemplate.presentation.blank.BlankBinder
import com.apps65.mvitemplate.presentation.blank.BlankFragment
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.apps65.mvitemplate.presentation.navigation.FragmentRouter
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Provider
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FeatureScope

/**
 * Важно! Не допускается дублирование именований компонентов.
 */
@FeatureScope
@DefineComponent(parent = FragmentComponent::class)
interface BlankComponent

@DefineComponent.Builder
interface BlankComponentBuilder {
    fun bindRouter(@BindsInstance @FragmentRouter(BlankFragment::class) router: AppRouter): BlankComponentBuilder
    fun build(): BlankComponent
}

@EntryPoint
@InstallIn(BlankComponent::class)
interface FeatureEntryPoint {
    fun getBinder(): Provider<BlankBinder>
}
