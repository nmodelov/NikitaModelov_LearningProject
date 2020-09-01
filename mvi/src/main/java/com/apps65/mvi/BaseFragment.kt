package com.apps65.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseView<*, *>>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    abstract val binder: Binder<V>

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MviLifecycleObserver(binder, viewLifecycleOwner)
    }
}
