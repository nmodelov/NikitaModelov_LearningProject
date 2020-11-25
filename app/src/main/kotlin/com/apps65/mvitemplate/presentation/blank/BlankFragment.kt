package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.BaseFragment
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentBlankBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class BlankFragment : BaseFragment<BlankView>(R.layout.fragment_blank) {

    companion object {
        fun newInstance(): BlankFragment = BlankFragment()
    }

    @Inject
    lateinit var binderProvider: Provider<BlankBinder>

    override val binder by viewModelFrom { binderProvider }
    override val viewImpl by lazy(mode = LazyThreadSafetyMode.NONE) { BlankViewImpl(::binding) }

    private val binding by viewBinding(FragmentBlankBinding::bind)
}
