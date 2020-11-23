package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.binding.BindingView
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentBlankBinding

class BlankViewImpl(binding: () -> FragmentBlankBinding) :
    BindingView<FragmentBlankBinding, BlankView.Model, BlankView.Event>(binding), BlankView {

    init {
        with(binding()) {
            blankButton.setOnClickListener {
                dispatch(BlankView.Event.OnBlankClick)
            }
            resultButton.setOnClickListener {
                dispatch(BlankView.Event.OnResultClick)
            }
        }
    }

    override fun render(binding: FragmentBlankBinding, model: BlankView.Model) {
        binding.counter.text = model.blankCount.toString()
        if (model.connection) {
            binding.connection.setText(R.string.connected)
        } else {
            binding.connection.setText(R.string.not_connected)
        }
    }
}
