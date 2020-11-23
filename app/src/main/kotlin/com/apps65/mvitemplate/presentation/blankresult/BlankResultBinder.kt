package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.Binder
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BlankResultBinder @Inject constructor(
    private val blankStore: BlankResultStore
) : Binder<BlankResultView>() {
    init {
        binderLifecycle.doOnDestroy(blankStore::dispose)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: BlankResultView) {
        val mainContext = Dispatchers.Main.immediate

        // обновление интерфейса и навигация
        bind(viewLifecycle, BinderLifecycleMode.START_STOP, mainContext) {
            blankStore.states.map(stateToModel) bindTo view
        }
    }

    private val stateToModel: suspend (BlankResultStore.State.() -> BlankResultView.Model) = {
        when (this) {
            is BlankResultStore.State.Blank -> BlankResultView.Model(this.blankCount)
        }
    }
}
