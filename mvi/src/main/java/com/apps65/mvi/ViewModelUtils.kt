package com.apps65.mvi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class ViewModelFactory<VM : ViewModel>(private val viewModelProvider: Provider<VM>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModelProvider.get() as T
}

class ViewModelOnStartLazy<VM : ViewModel>(
    lifecycleOwner: LifecycleOwner,
    lazy: ViewModelLazy<VM>
) : Lazy<VM> by lazy {

    init {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStart() {
                value // init viewModel
            }
        })
    }
}

inline fun <reified VM : ViewModel> Fragment.viewModelFrom(
    noinline provider: () -> Provider<VM>
): Lazy<VM> {
    return ViewModelOnStartLazy(
        this,
        ViewModelLazy(
            VM::class,
            { this.viewModelStore },
            { ViewModelFactory(provider.invoke()) })
    )
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModelFrom(
    noinline provider: () -> Provider<VM>
): Lazy<VM> {
    return ViewModelOnStartLazy(
        this,
        ViewModelLazy(
            VM::class,
            { this.viewModelStore },
            { ViewModelFactory(provider.invoke()) })
    )
}
