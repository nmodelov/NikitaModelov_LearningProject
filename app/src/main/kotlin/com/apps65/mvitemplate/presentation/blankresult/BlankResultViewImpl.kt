package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.binding.BindingView
import com.apps65.mvitemplate.databinding.FragmentResultBinding
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Intent
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.State

class BlankResultViewImpl(binding: () -> FragmentResultBinding) :
    BindingView<FragmentResultBinding, State, Intent>(binding), BlankResultView {

    override fun render(binding: FragmentResultBinding, model: State) {
        if (model is State.Blank) {
            binding.counter.text = model.blankCount.toString()
        }
    }
}
