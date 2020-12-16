package ${packageName}.presentation.${featurePackage}

import com.apps65.mvi.SimpleBinder
import com.apps65.mvi.common.DispatchersProvider
import ${packageName}.domain.${featurePackage}.store.${featureName}Store
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Intent
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Label
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State
import javax.inject.Inject

/**
 * Binder which uses Model for representing model State and Event for representing Intent
 */
class ${featureName}Binder @Inject constructor(
    store: ${featureName}Store,
    dispatchersProvider: DispatchersProvider,
) : SimpleBinder<Intent, State, Label, ${featureName}View>(
    store,
    dispatchersProvider
) {
    override fun handleLabel(label: Label) {
        when (label) {

        }
    }
}
