package com.apps65.mvitemplate.domain.blank.store

import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.common.SuspendDelegationExecutor
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Action
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory.Result
import com.arkivanov.mvikotlin.core.store.Executor
import javax.inject.Inject

internal typealias BlankExecutor = () -> Executor<Intent, Action, State, Result, Label>

internal class ExecutorsFactory @Inject constructor(
    private val dispatchersProvider: DispatchersProvider
) {
    fun create(): BlankExecutor = {
        object : SuspendDelegationExecutor<Intent, Action, State, Result, Label>(
            dispatchersProvider
        ) {
            // Если обработка интента не требует доп. зависимостей и какой-то сложной логики
            // то лучше вынести в отдельный метод. В примере - executeIncrement
            // в when блоке - только однострочные конструкции
            // Если обработка интента сложная и требует доп. зависимостей то лучше сделать отдельным классом
            override suspend fun executeIntent(intent: Intent, getState: () -> State) {
                when (intent) {
                    is Intent.Increment -> executeIncrement()
                }
            }

            override suspend fun executeAction(action: Action, getState: () -> State) {
                when (action) {
                    is Action.Connection -> dispatch(Result.ChangeConnection(action.connected))
                }
            }

            private suspend fun executeIncrement() {
                publish(Label.Blank)
                dispatch(Result.Increment(1))
            }
        }
    }
}
