package com.apps65.mvitemplate.presentation.subnavigation

import androidx.activity.OnBackPressedCallback
import com.apps65.mvi.binding.BindingView
import com.apps65.mvitemplate.databinding.FragmentSubnavSampleBinding
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.AddTo3
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.BackPressed
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.OpenNext
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.OpenNextGlobal
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.SwitchTo2
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.SwitchTo3AndOpen
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent.UpdateBSCount
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State

class SubNavViewImpl(
    binding: () -> FragmentSubnavSampleBinding,
    private val backStackCount: () -> Int
) :
    BindingView<FragmentSubnavSampleBinding, State, Intent>(binding), SubNavView {
    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            dispatch(BackPressed)
        }
    }

    init {
        with(binding()) {
            switchTo2.setOnClickListener {
                dispatch(SwitchTo2)
            }
            switchto3AndOpen.setOnClickListener {
                dispatch(SwitchTo3AndOpen)
            }
            addTo3.setOnClickListener {
                dispatch(AddTo3)
            }
            openNext.setOnClickListener {
                dispatch(OpenNext)
            }
            openNextGlobal.setOnClickListener {
                dispatch(OpenNextGlobal)
            }
        }
    }

    override fun render(binding: FragmentSubnavSampleBinding, model: State) {
        with(binding) {
            label.text = buildString {
                for (i in 0..model.count) {
                    if (i == 0) {
                        append("${i + 1}")
                    } else {
                        append("->${i + 1}")
                    }
                }
            }
        }
    }

    fun onStart(subNavFragment: SubNavFragment) {
        subNavFragment.requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        dispatch(UpdateBSCount(backStackCount()))
    }

    fun onStop() {
        onBackPressedCallback.remove()
    }
}
