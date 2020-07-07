package com.apps65.mvitemplate.domain.blank.store

import com.apps65.mvi.saving.SavedStateKeeper
import com.apps65.mvikotlinrxjava.RxJavaExecutor
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Action
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import javax.inject.Inject

internal class BlankStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val stateKeeper: SavedStateKeeper
) {
    companion object {
        private const val BLANK_STORE_STATE = "blank_store_state"
    }

    fun create(): BlankStore {
        return object : BlankStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "BlankStore",
                initialState = getInitialState(),
                executorFactory = executorFactory,
                reducer = reducer
            ) {}
            .also { registerStateKeeper(it) }
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

    private val executorFactory = {
        object : SuspendExecutor<Intent, Action, State, Result, Label>() {
            override suspend fun executeIntent(intent: Intent, getState: () -> State) {
                val state = getState.invoke()
                if (intent is Intent.Blank && state is State.Blank) {
                    publish(Label.Blank)
                    dispatch(Result.Blank(state.blankCount + 1))
                }
            }
        }
    }

    private val reducer = object : Reducer<State, Result> {
        override fun State.reduce(result: Result): State {
            return if (this is State.Blank && result is Result.Blank) {
                this.copy(blankCount = result.blankCount)
            } else {
                this
            }
        }
    }

    private sealed class Result {
        data class Blank(val blankCount: Int) : Result()
    }
}
