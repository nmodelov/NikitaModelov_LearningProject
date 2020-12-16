package ${packageName}.presentation.${featurePackage}

import com.apps65.mvi.binding.BindingView
import ${packageName}.databinding.Fragment${featureName}Binding
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.Intent
import ${packageName}.domain.${featurePackage}.store.${featureName}Store.State

class ${featureName}ViewImpl(binding: () -> Fragment${featureName}Binding) :
    BindingView<Fragment${featureName}Binding, State, Intent>(binding), ${featureName}View {

    init {
        with(binding()) {

        }
    }

    override fun render(binding: Fragment${featureName}Binding, model: State) {
        with(binding) {

        }
    }
}
