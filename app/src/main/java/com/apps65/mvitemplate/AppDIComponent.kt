package com.apps65.mvitemplate

import android.app.Application
import com.apps65.mvitemplate.data.DataDIModule
import com.apps65.mvitemplate.domain.DomainDIModule
import com.apps65.mvitemplate.presentation.di.PresentationDIModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DataDIModule::class,
        DomainDIModule::class,
        PresentationDIModule::class,
        AppDIModule::class
    ]
)

@Singleton
interface AppDIComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppDIComponent
    }

    fun inject(app: App)
}
