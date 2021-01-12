package com.apps65.mvitemplate.domain.main.store

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvitemplate.domain.main.store.MainStore.Action
import com.apps65.mvitemplate.domain.main.store.MainStore.Intent
import com.apps65.mvitemplate.domain.main.store.MainStore.Label
import com.apps65.mvitemplate.domain.main.store.MainStore.State
import com.apps65.mvitemplate.presentation.di.ActivityKeeper
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import javax.inject.Inject

private const val MAIN_STORE_STATE = "main_store_state"

internal class MainStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    @ActivityKeeper
    private val stateKeeper: SavedStateKeeper
) {

    fun create(): MainStore {
        val storeDelegate = storeFactory.create(
            name = "MainStore",
            initialState = getInitialState(),
            executorFactory = executorFactory,
            reducer = object : Reducer<State, State> {
                override fun State.reduce(result: State) = result
            },
            bootstrapper = SimpleBootstrapper(Action.Blank)
        )

        return object : MainStore, Store<Intent, State, Label> by storeDelegate {
            override fun dispose() {
                storeDelegate.dispose()
                stateKeeper.unregister()
            }
        }.also { registerStateKeeper(it) }
    }

    private fun getInitialState(): State {
        val savedState = stateKeeper.get<State>(MAIN_STORE_STATE)
        return savedState ?: State.Init
    }

    private fun registerStateKeeper(store: MainStore) {
        stateKeeper.register(store, MAIN_STORE_STATE) {
            this.state // in this case just providing the state without changes
            // you can provide provide a state which is different from the current state of store,
            // for example if you don't want to show loading after restoring the state
        }
    }

    private val executorFactory = {
        object : SuspendExecutor<Intent, Any, State, State, Label>() {
            override suspend fun executeAction(action: Any, getState: () -> State) {
                super.executeAction(action, getState)
                if (getState() == State.Init && action == Action.Blank) {
                    dispatch(State.Idle)
                    publish(Label.Started)
                }
            }
        }
    }
}
