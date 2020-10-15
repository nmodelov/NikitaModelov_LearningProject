package com.apps65.mvitemplate.domain.common

import com.arkivanov.mvikotlin.core.annotations.MainThread

interface IntentExecutorDelegate<Intent : Any, State : Any, Result : Any, Label : Any> {
    @MainThread
    suspend fun executeIntent(
        intent: Intent,
        getState: () -> State,
        dispatch: suspend (Result) -> Unit,
        publish: suspend (Label) -> Unit
    )
}

interface ActionExecutorDelegate<Action : Any, State : Any, Result : Any, Label : Any> {
    @MainThread
    suspend fun executeAction(
        action: Action,
        getState: () -> State,
        dispatch: suspend (Result) -> Unit,
        publish: suspend (Label) -> Unit
    )
}
