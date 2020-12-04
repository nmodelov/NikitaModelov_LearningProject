package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.binding.BindingView
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentBlankBinding
import com.apps65.mvitemplate.domain.blank.store.BlankStore

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
            rollDice.setOnClickListener {
                dispatch(BlankView.Event.RollDice)
            }
        }
    }

    override fun render(binding: FragmentBlankBinding, model: BlankView.Model) {
        binding.counter.text = model.blankCount.toString()
        binding.diceState.text = model.diceState.diceStateLabel()
        if (model.connection) {
            binding.connection.setText(R.string.connected)
        } else {
            binding.connection.setText(R.string.not_connected)
        }
    }

    private fun BlankStore.DiceState?.diceStateLabel(): String {
        return when (this) {
            is BlankStore.DiceState.Spinning -> {
                context.getString(R.string.dice_spinning)
            }
            is BlankStore.DiceState.DiceIdle -> {
                value.toString()
            }
            null -> {
                ""
            }
        }
    }
}
