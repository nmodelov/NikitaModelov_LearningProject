package ${packageName}.presentation.${featurePackage}

import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewFrom
import com.apps65.mvi.viewModelFrom
import ${packageName}.R
import ${packageName}.databinding.Fragment${featureName}Binding
import com.apps65.mvitemplate.presentation.base.BaseStateFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class ${featureName}Fragment : BaseStateFragment<${featureName}View>(R.layout.${fragmentName}) {

    companion object {
        fun newInstance(): ${featureName}Fragment = ${featureName}Fragment()
    }

    @Inject
    lateinit var binderProvider: Provider<${featureName}Binder>
    override val binder by viewModelFrom { binderProvider }
    override val viewImpl by viewFrom { ${featureName}ViewImpl(::binding) }

    private val binding by viewBinding(Fragment${featureName}Binding::bind)
}
