package com.apps65.mvi

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.apps65.mvi.saving.StateBundleKeeper

abstract class BaseActivity<V : BaseView<*, *>> : AppCompatActivity() {
    abstract val stateBundleKeeper: StateBundleKeeper
    abstract val binder: Binder<V>
    abstract val viewImpl: V

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateBundleKeeper.restoreState(savedInstanceState)
        MviLifecycleObserver(binder, this)
        binder.onViewCreated(viewImpl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stateBundleKeeper.saveState(outState)
    }
}
