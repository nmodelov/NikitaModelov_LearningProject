package com.apps65.mvitemplate.domain.subnav

import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.apps65.mvitemplate.presentation.navigation.FragmentRouter
import com.apps65.mvitemplate.presentation.subnavigation.SubNavBinder
import com.apps65.mvitemplate.presentation.subnavigation.SubNavFragment
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
interface SubNavComponent

@DefineComponent.Builder
interface SubNavComponentBuilder {
    fun bindRouter(@BindsInstance @FragmentRouter(SubNavFragment::class) router: AppRouter): SubNavComponentBuilder
    fun build(): SubNavComponent
}

@EntryPoint
@InstallIn(SubNavComponent::class)
interface FeatureEntryPoint {
    fun getBinder(): Provider<SubNavBinder>
}
