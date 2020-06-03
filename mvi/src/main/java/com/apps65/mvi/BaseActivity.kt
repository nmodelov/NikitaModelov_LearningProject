package com.apps65.mvi

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseView<*, *>> : AppCompatActivity() {
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

    override fun onDestroy() {
        binder.onViewDestroyed()
        super.onDestroy()
    }
}
