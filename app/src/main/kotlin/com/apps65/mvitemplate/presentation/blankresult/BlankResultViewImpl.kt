package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.binding.BindingView
import com.apps65.mvitemplate.databinding.FragmentResultBinding

class BlankResultViewImpl(binding: () -> FragmentResultBinding) :
    BindingView<FragmentResultBinding, BlankResultView.Model, BlankResultView.Event>(binding), BlankResultView {

    override fun render(binding: FragmentResultBinding, model: BlankResultView.Model) {
        binding.counter.text = model.blankCount.toString()
    }
}
