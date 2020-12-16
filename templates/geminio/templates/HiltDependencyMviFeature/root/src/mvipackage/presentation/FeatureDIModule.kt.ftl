package ${packageName}.presentation.${featurePackage}

import com.apps65.mvi.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import ${packageName}.domain.${featurePackage}.store.Args
import javax.inject.Provider
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import javax.inject.Scope

@Module
@InstallIn(FragmentComponent::class)
object FeatureDIModule {
    @Provides
    @FragmentScoped
    fun provideVMFactory(provider: Provider<${featureName}Binder>): ViewModelFactory<${featureName}Binder> =
        ViewModelFactory(provider)
}

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ${featureName}Scope

@${featureName}Scope
@DefineComponent(parent = FragmentComponent::class)
interface ${featureName}Component

@DefineComponent.Builder
interface ${featureName}ComponentBuilder {
    fun bindArgs(@BindsInstance count: Args): ${featureName}ComponentBuilder
    fun build(): ${featureName}Component
}

@EntryPoint
@InstallIn(${featureName}Component::class)
interface ${featureName}EntryPoint {
    fun getBinder(): Provider<${featureName}Binder>
}
