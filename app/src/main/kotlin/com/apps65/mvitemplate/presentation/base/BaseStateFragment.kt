package com.apps65.mvitemplate.presentation.base

import androidx.annotation.LayoutRes
import com.apps65.mvi.BaseFragment
import com.apps65.mvi.BaseView
import com.apps65.mvi.saving.StateBundleKeeper
import javax.inject.Inject

abstract class BaseStateFragment<V : BaseView<*, *>>(@LayoutRes layoutId: Int) : BaseFragment<V>(layoutId) {
    @Inject
    lateinit var stateKeeper: StateBundleKeeper
    override val stateBundleKeeper by lazy(mode = LazyThreadSafetyMode.NONE) { stateKeeper }
}
