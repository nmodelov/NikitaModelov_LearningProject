package com.apps65.mvitemplate.domain.main.store

import com.apps65.mvitemplate.domain.main.store.MainStore.Intent
import com.apps65.mvitemplate.domain.main.store.MainStore.Label
import com.apps65.mvitemplate.domain.main.store.MainStore.State
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import javax.inject.Inject

internal class MainStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory
) {

    fun create(): MainStore {
        return object : MainStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "MainStore",
                initialState = State.Idle,
                executorFactory = executorFactory
            ) {}
    }

    private val executorFactory = {
        object : SuspendExecutor<Intent, Any, State, State, Label>() {
            override suspend fun executeIntent(intent: Intent, getState: () -> State) {
                publish(Label.Started)
            }
        }
    }
}
