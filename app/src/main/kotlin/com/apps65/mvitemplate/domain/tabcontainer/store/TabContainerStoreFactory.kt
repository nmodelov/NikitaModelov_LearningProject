package com.apps65.mvitemplate.domain.tabcontainer.store

import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.common.SuspendDelegationExecutor
import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Intent
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Label
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.State
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import javax.inject.Inject

private const val STATE = "tab_container_state"

internal class TabContainerStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val stateKeeper: SavedStateKeeper,
    private val dispatchersProvider: DispatchersProvider
) {

    fun create(): TabContainerStore {
        return object :
            TabContainerStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "TabContainerStore",
                initialState = getInitialState(),
                executorFactory = executorFactory
            ) {}
            .also { registerStateKeeper(it) }
    }

    private fun getInitialState(): State {
        val savedState = stateKeeper.get<State>(STATE)
        return savedState ?: State.Blank()
    }

    private fun registerStateKeeper(store: TabContainerStore) {
        stateKeeper.register(store, STATE) {
            this.state
        }
    }

    private val executorFactory = {
        object : SuspendDelegationExecutor<Intent, Any, State, State, Label>(
            dispatchersProvider
        ) {
            override suspend fun executeIntent(intent: Intent, getState: () -> State) {
                when (intent) {
                    is Intent.SwitchTab -> publish(Label.SwitchedTab(intent.menuItemId))
                    is Intent.ReselectTab -> publish(Label.ReselectedTab(intent.tag))
                }
            }
        }
    }
}
