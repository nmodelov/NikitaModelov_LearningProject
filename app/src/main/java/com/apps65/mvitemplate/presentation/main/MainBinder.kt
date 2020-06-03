package com.apps65.mvitemplate.presentation.main

import com.apps65.mvi.Binder
import com.apps65.mvitemplate.domain.main.store.MainStore
import com.apps65.mvitemplate.presentation.blank.BlankScreen
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class MainBinder @Inject constructor(
    private val mainStore: MainStore,
    private val router: Router
) : Binder<MainView>() {

    init {
        binderLifecycle.doOnDestroy(mainStore::dispose)
        bind(binderLifecycle, BinderLifecycleMode.START_STOP) {
            mainStore.labels bindTo { handelLabel(it) }
            flowOf(MainStore.Intent.Start) bindTo mainStore
        }
    }

    private fun handelLabel(label: MainStore.Label) {
        when (label) {
            MainStore.Label.Started -> router.newRootScreen(BlankScreen)
        }
    }
}
