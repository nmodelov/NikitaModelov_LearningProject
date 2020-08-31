package com.apps65.mvi

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseView<*, *>>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    abstract val binder: Binder<V>

    override fun onStart() {
        super.onStart()
        binder.onStart()
    }

    override fun onResume() {
        super.onResume()
        binder.onResume()
    }

    override fun onPause() {
        binder.onPause()
        super.onPause()
    }

    override fun onStop() {
        binder.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        binder.onViewDestroyed()
        super.onDestroyView()
    }

    override fun onDestroy() {
        binder.onViewDestroyed()
        super.onDestroy()
    }
}
