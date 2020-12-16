package ${packageName}.presentation.${featurePackage}

import com.apps65.mvi.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Provider

@Module
@InstallIn(FragmentComponent::class)
object FeatureDIModule {
    @Provides
    @FragmentScoped
    fun provideVMFactory(provider: Provider<${featureName}Binder>): ViewModelFactory<${featureName}Binder> =
        ViewModelFactory(provider)
}
