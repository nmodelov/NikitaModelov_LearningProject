package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.BaseFragment
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewFrom
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentBlankBinding
import com.apps65.mvitemplate.domain.blank.BlankComponentBuilder
import com.apps65.mvitemplate.domain.blank.FeatureEntryPoint
import com.apps65.mvitemplate.presentation.subnavigation.tab.router
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BlankFragment : BaseFragment<BlankView>(R.layout.fragment_blank) {

    companion object {
        fun newInstance(): BlankFragment = BlankFragment()
    }

    @Inject
    lateinit var componentBuilder: BlankComponentBuilder
    override val binder by viewModelFrom {
        val component = componentBuilder
            .bindRouter(router())
            .build()
        EntryPoints.get(component, FeatureEntryPoint::class.java).getBinder()
    }
    override val viewImpl by viewFrom { BlankViewImpl(::binding) }

    private val binding by viewBinding(FragmentBlankBinding::bind)
}
