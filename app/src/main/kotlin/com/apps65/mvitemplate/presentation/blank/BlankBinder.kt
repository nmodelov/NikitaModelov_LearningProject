package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.Binder
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvitemplate.domain.blank.store.BlankStore
import com.apps65.mvitemplate.presentation.blankresult.BlankResultScreen
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class BlankBinder @Inject constructor(
    private val blankStore: BlankStore,
    private val router: Router,
    private val dispatchersProvider: DispatchersProvider
) : Binder<BlankView>() {
    init {
        binderLifecycle.doOnDestroy(blankStore::dispose)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: BlankView) {
        val mainContext = dispatchersProvider.main

        // обновление интерфейса и навигация
        bind(viewLifecycle, BinderLifecycleMode.START_STOP, mainContext) {
            blankStore.states.map(stateToModel) bindTo view
            blankStore.labels bindTo { handleLabel(it) }
        }

        // команды от view-controller, диапазон шире т.к. возможно что-то типа onActivityResult
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, mainContext) {
            view.events.map(eventToIntent) bindTo blankStore
        }
    }

    private fun handleLabel(label: BlankStore.Label) {
        when (label) {
            BlankStore.Label.Blank -> Timber.i("$label has been received")
            is BlankStore.Label.Result -> router.replaceScreen(BlankResultScreen(label.count))
        }
    }

    private val stateToModel: suspend (BlankStore.State.() -> BlankView.Model) = {
        when (this) {
            is BlankStore.State.Blank -> BlankView.Model(this.blankCount, this.connected)
        }
    }

    private val eventToIntent: suspend (BlankView.Event.() -> BlankStore.Intent) = {
        when (this) {
            BlankView.Event.OnBlankClick -> BlankStore.Intent.Increment
            BlankView.Event.OnResultClick -> BlankStore.Intent.OnResult
        }
    }
}
