package com.apps65.mvitemplate.presentation.di

import com.apps65.mvitemplate.presentation.blank.BlankFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentDIModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun bindBlankFragment(): BlankFragment
}
