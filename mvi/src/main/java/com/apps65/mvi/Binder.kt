package com.apps65.mvi

import androidx.lifecycle.ViewModel
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.LifecycleRegistry

abstract class Binder<V : BaseView<*, *>> : ViewModel() {

    private val binderLifecycleRegistry = LifecycleRegistry()

    val binderLifecycle: Lifecycle
        get() {
            return binderLifecycleRegistry
        }

    private var viewLifecycleRegistry: LifecycleRegistry? = null

    val viewLifecycle: Lifecycle
        get() {
            viewLifecycleRegistry = viewLifecycleRegistry ?: LifecycleRegistry()
            return viewLifecycleRegistry!!
        }

    init {
        binderLifecycleRegistry.onCreate()
        binderLifecycleRegistry.onStart()
        binderLifecycleRegistry.onResume()
    }

    open fun onViewCreated(view: V) {
        viewLifecycleRegistry = viewLifecycleRegistry ?: LifecycleRegistry()
        viewLifecycleRegistry?.onCreate()
    }

    fun onStart() {
        viewLifecycleRegistry?.onStart()
    }

    fun onResume() {
        viewLifecycleRegistry?.onResume()
    }

    fun onPause() {
        viewLifecycleRegistry?.onPause()
    }

    fun onStop() {
        viewLifecycleRegistry?.onStop()
    }

    fun onViewDestroyed() {
        viewLifecycleRegistry?.onDestroy()
        viewLifecycleRegistry = null
    }

    override fun onCleared() {
        binderLifecycleRegistry.onPause()
        binderLifecycleRegistry.onStop()
        binderLifecycleRegistry.onDestroy()
        super.onCleared()
    }
}
