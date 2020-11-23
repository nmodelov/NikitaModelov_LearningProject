package com.apps65.mvitemplate.domain.blankresult.store

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Intent
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Label
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.State
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import javax.inject.Inject

private const val STATE = "blank_result_store_state"

data class Args(val int: Int)

internal class BlankResultStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val stateKeeper: SavedStateKeeper,
    private val initialCount: Args
) {

    fun create(): BlankResultStore {
        return object :
            BlankResultStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "BlankStore",
                initialState = getInitialState(),
                executorFactory = emptyExecutorFactory
            ) {}
            .also { registerStateKeeper(it) }
    }

    private fun getInitialState(): State {
        val savedState = stateKeeper.get<State>(STATE)
        return savedState ?: State.Blank(initialCount.int)
    }

    private fun registerStateKeeper(store: BlankResultStore) {
        stateKeeper.register(store, STATE) {
            this.state
        }
    }

    private val emptyExecutorFactory = {
        object : SuspendExecutor<Intent, Any, State, State, Label>() {
            override suspend fun executeAction(action: Any, getState: () -> State) {
                super.executeAction(action, getState)
            }
        }
    }
}
