package com.apps65.mvitemplate.presentation.blankresult

import androidx.core.os.bundleOf
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewFrom
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentResultBinding
import com.apps65.mvitemplate.domain.blankresult.FeatureComponentBuilder
import com.apps65.mvitemplate.domain.blankresult.FeatureEntryPoint
import com.apps65.mvitemplate.domain.blankresult.store.Args
import com.apps65.mvitemplate.presentation.base.BaseStateFragment
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BlankResultFragment : BaseStateFragment<BlankResultView>(R.layout.fragment_result) {
    companion object {
        const val ARGS = "ARGS_COUNT"
        fun newInstance(count: Int): BlankResultFragment = BlankResultFragment().apply {
            arguments = bundleOf(ARGS to count)
        }
    }

    @Inject
    lateinit var componentBuilder: FeatureComponentBuilder

    override val binder by viewModelFrom {
        val count = requireArguments().getInt(ARGS)
        val component = componentBuilder.bindArgs(Args(count)).build()
        EntryPoints.get(component, FeatureEntryPoint::class.java).getBinder()
    }
    override val viewImpl by viewFrom { BlankResultViewImpl(::binding) }
    private val binding by viewBinding(FragmentResultBinding::bind)
}
