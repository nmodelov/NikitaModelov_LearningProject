package com.apps65.mvitemplate.presentation.subnavigation

import com.apps65.mvi.BaseFragment
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewFrom
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentSubnavSampleBinding
import com.apps65.mvitemplate.domain.subnav.FeatureEntryPoint
import com.apps65.mvitemplate.domain.subnav.SubNavComponentBuilder
import com.apps65.mvitemplate.presentation.subnavigation.tab.router
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubNavFragment : BaseFragment<SubNavView>(R.layout.fragment_subnav_sample) {
    companion object {
        fun newInstance() = SubNavFragment()
    }

    @Inject
    lateinit var componentBuilder: SubNavComponentBuilder
    override val binder by viewModelFrom {
        val component = componentBuilder
            .bindRouter(router())
            .build()
        EntryPoints.get(component, FeatureEntryPoint::class.java).getBinder()
    }
    override val viewImpl by viewFrom {
        SubNavViewImpl(::binding) {
            backStackCount()
        }
    }

    override fun onStart() {
        super.onStart()
        viewImpl.onStart(this)
    }

    override fun onStop() {
        viewImpl.onStop()
        super.onStop()
    }

    private val binding by viewBinding(FragmentSubnavSampleBinding::bind)

    private fun backStackCount(): Int {
        return when (parentFragment) {
            null -> {
                requireActivity().supportFragmentManager.backStackEntryCount
            }
            else -> {
                requireParentFragment().childFragmentManager.backStackEntryCount
            }
        }
    }
}
