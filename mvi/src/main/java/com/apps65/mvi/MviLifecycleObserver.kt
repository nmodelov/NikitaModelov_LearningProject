package com.apps65.mvi

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MviLifecycleObserver(
    private val binder: Binder<*>,
    owner: LifecycleOwner
) : DefaultLifecycleObserver {

    init {
        owner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate(owner: LifecycleOwner) {
        binder.onCreate()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart(owner: LifecycleOwner) {
        binder.onStart()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume(owner: LifecycleOwner) {
        binder.onResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause(owner: LifecycleOwner) {
        binder.onPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop(owner: LifecycleOwner) {
        binder.onStop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy(owner: LifecycleOwner) {
        binder.onViewDestroyed()
        owner.lifecycle.removeObserver(this)
    }
}
