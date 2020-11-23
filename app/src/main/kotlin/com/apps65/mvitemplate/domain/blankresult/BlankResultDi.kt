package com.apps65.mvitemplate.domain.blankresult

import com.apps65.mvitemplate.domain.blankresult.store.Args
import com.apps65.mvitemplate.presentation.blankresult.BlankResultBinder
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

@FeatureScope
@DefineComponent(parent = FragmentComponent::class)
interface FeatureComponent

@DefineComponent.Builder
interface FeatureComponentBuilder {
    fun bindArgs(@BindsInstance count: Args): FeatureComponentBuilder
    fun build(): FeatureComponent
}

@EntryPoint
@InstallIn(FeatureComponent::class)
interface FeatureEntryPoint {
    fun getBinder(): Provider<BlankResultBinder>
}
