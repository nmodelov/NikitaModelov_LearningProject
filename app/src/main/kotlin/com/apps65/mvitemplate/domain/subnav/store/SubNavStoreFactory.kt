package com.apps65.mvitemplate.domain.subnav.store

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import javax.inject.Inject

private const val STATE = "blank_result_store_state"

internal class SubNavStoreFactory @Inject constructor(
    private val subNavExecutorsFactory: SubNavExecutorsFactory,
    private val storeFactory: StoreFactory,
    private val stateKeeper: SavedStateKeeper,
) {

    fun create(): SubNavStore {
        val storeDelegate = storeFactory.create(
            name = "SubNavStore",
            initialState = getInitialState(),
            executorFactory = subNavExecutorsFactory.create(),
            reducer = SubNavReducer()
        )

        return object : SubNavStore, Store<Intent, State, Label> by storeDelegate {
            override fun dispose() {
                storeDelegate.dispose()
                stateKeeper.unregister()
            }
        }.also { registerStateKeeper(it) }
    }

    private fun getInitialState(): State {
        val savedState = stateKeeper.get<State>(STATE)
        return savedState ?: State(1)
    }

    private fun registerStateKeeper(store: SubNavStore) {
        stateKeeper.register(store, STATE) {
            this.state
        }
    }
}
