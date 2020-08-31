package com.apps65.mvi

import com.arkivanov.mvikotlin.core.store.Executor

abstract class AbstractExecutor<Intent : Any, Action : Any, State : Any, Result : Any, Label : Any> :
    Executor<Intent, Action, State, Result, Label> {

    protected abstract val executors: Set<Executor<*, *, State, Result, Label>>

    override fun init(callbacks: Executor.Callbacks<State, Result, Label>) {
        executors.forEach { it.init(callbacks) }
    }

    override fun dispose() {
        executors.forEach { it.dispose() }
    }
}
