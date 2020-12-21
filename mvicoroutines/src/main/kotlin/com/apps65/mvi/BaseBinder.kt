package com.apps65.mvi

import com.apps65.mvi.common.DispatchersProvider
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

abstract class BaseBinder<
    Intent : Any,
    State : Any,
    Label : Any,
    Event : Any,
    Model : Any,
    View : BaseView<Model, Event>
    >(val store: Store<Intent, State, Label>, val dispatchersProvider: DispatchersProvider) : Binder<View>() {
    init {
        if (store.isDisposed) {
            throw IllegalStateException("Store is disposed! Please, check DI scope")
        }
        binderLifecycle.doOnDestroy { store.dispose() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View) {
        bindFlows(view)
    }

    /**
     * Handle single action events
     */
    protected open fun handleLabel(label: Label) {
        // to override
    }

    protected open fun bindFlows(
        view: View,
        context: CoroutineContext = dispatchersProvider.main
    ) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP, context) {
            modelFlow() bindTo view
            labelFlow() bindTo { handleLabel(it) }
        }
        // команды от view-controller, диапазон шире т.к. возможно что-то типа onActivityResult
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, context) {
            intentFlow(view) bindTo store
        }
    }

    abstract fun modelFlow(): Flow<Model>
    abstract fun labelFlow(): Flow<Label>
    abstract fun intentFlow(view: View): Flow<Intent>
}
