package ${packageName}.presentation.${featurePackage}

import com.apps65.mvi.BaseView
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Intent
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State

interface ${featureName}View : BaseView<State, Intent>
