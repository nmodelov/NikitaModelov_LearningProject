package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentBlankBinding
import com.arkivanov.mvikotlin.core.view.BaseMviView

class BlankViewImpl(private val binding: FragmentBlankBinding) :
    BaseMviView<BlankView.Model, BlankView.Event>(), BlankView {

    init {
        binding.blankButton.setOnClickListener {
            dispatch(BlankView.Event.OnBlankClick)
        }
    }

    override fun render(model: BlankView.Model) {
        binding.counter.text = model.blankCount.toString()
        if (model.connection) {
            binding.connection.setText(R.string.connected)
        } else {
            binding.connection.setText(R.string.not_connected)
        }

        super.render(model)
    }
}
