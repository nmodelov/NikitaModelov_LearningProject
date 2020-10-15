package com.apps65.mvitemplate.presentation.main

import com.apps65.mvi.Binder
import com.apps65.mvitemplate.domain.main.store.MainStore
import com.apps65.mvitemplate.presentation.blank.BlankScreen
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class MainBinder @Inject constructor(
    private val mainStore: MainStore,
    private val router: Router
) : Binder<MainView>() {

    init {
        binderLifecycle.doOnDestroy(mainStore::dispose)
    }

    override fun onViewCreated(view: MainView) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, Dispatchers.Main.immediate) {
            mainStore.labels bindTo { handelLabel(it) }
            view.events.map(eventToIntent) bindTo mainStore
        }
    }

    private fun handelLabel(label: MainStore.Label) {
        when (label) {
            MainStore.Label.Started -> router.newRootScreen(BlankScreen)
        }
    }

    private val eventToIntent: suspend (MainView.Event.() -> MainStore.Intent) = {
        when (this) {
            MainView.Event.OnAppStart -> MainStore.Intent.Start
        }
    }
}
