package ${packageName}.domain.${featurePackage}

import ${packageName}.domain.${featurePackage}.store.${featureName}StoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object ${featureName}DIModule {
    @Provides
    @ActivityRetainedScoped
    internal fun provide${featureName}Store(factory: ${featureName}StoreFactory) = factory.create()
}
