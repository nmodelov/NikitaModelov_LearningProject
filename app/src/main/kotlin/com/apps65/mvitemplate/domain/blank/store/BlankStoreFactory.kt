package com.apps65.mvitemplate.domain.blank.store

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.apps65.netutils.connection.ConnectionService
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

private const val BLANK_STORE_STATE = "blank_store_state"

internal class BlankStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val stateKeeper: SavedStateKeeper,
    private val executorsFactory: ExecutorsFactory,
    private val connectionService: ConnectionService
) {

    fun create(): BlankStore {
        return object :
            BlankStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "BlankStore",
                initialState = getInitialState(),
                executorFactory = executorsFactory.create(),
                reducer = Reducer(),
                bootstrapper = bootstrapper
            ) {}
            .also { registerStateKeeper(it) }
    }

    private val bootstrapper = object : SuspendBootstrapper<BlankStore.Action>() {
        override suspend fun bootstrap() {
            connectionService.observeConnectionState()
                .collect {
                    dispatch(BlankStore.Action.Connection(it))
                }
        }
    }

    private fun getInitialState(): State {
        val savedState = stateKeeper.get<State>(BLANK_STORE_STATE)
        return savedState ?: State.Blank(0)
    }

    private fun registerStateKeeper(store: BlankStore) {
        stateKeeper.register(store, BLANK_STORE_STATE) {
            this.state // in this case just providing the state without changes
            // you can provide provide a state which is different from the current state of store,
            // for example if you don't want to show loading after restoring the state
        }
    }

    internal sealed class Result {
        data class Increment(val value: Int) : Result()
        data class ChangeConnection(val connected: Boolean) : Result()
    }
}
