package ${packageName}.presentation.${featurePackage}

import androidx.core.os.bundleOf
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewFrom
import com.apps65.mvi.viewModelFrom
import ${packageName}.R
import ${packageName}.databinding.Fragment${featureName}Binding
import ${packageName}.domain.${featurePackage}.store.Args
import com.apps65.mvitemplate.presentation.base.BaseStateFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class ${featureName}Fragment : BaseStateFragment<${featureName}View>(R.layout.${fragmentName}) {

    companion object {
        fun newInstance(args: Args): ${featureName}Fragment = ${featureName}Fragment().withInitialArguments(args)
    }

    @Inject
    lateinit var componentBuilder: ${featureName}ComponentBuilder

    override val binder: Binder<${featureName}View> by viewModelFrom {
        val component = componentBuilder.bindArgs(initialArguments()).build()
        EntryPoints.get(component, ${featureName}EntryPoint::class.java).getBinder()
    }

    override val viewImpl by viewFrom { ${featureName}ViewImpl(::binding) }

    private val binding by viewBinding(Fragment${featureName}Binding::bind)
}
