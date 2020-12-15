package com.apps65.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.apps65.mvi.saving.StateBundleKeeper

abstract class BaseFragment<V : BaseView<*, *>>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    protected abstract val stateBundleKeeper: StateBundleKeeper?
    protected abstract val binder: Binder<V>
    protected abstract val viewImpl: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateBundleKeeper?.restoreState(savedInstanceState)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MviLifecycleObserver(binder, viewLifecycleOwner)
        binder.onViewCreated(viewImpl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stateBundleKeeper?.saveState(outState)
    }
}
