package com.apps65.mvi

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseView<*, *>> : AppCompatActivity() {
    abstract val binder: Binder<V>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MviLifecycleObserver(binder, this)
    }
}
