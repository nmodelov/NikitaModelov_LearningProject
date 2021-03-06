package ${packageName}.domain.${featurePackage}

import ${packageName}.domain.${featurePackage}.store.${featureName}StoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ${featureName}DIModule {
    @Provides
    @FragmentScoped
    internal fun provide${featureName}Store(factory: ${featureName}StoreFactory) = factory.create()
}
