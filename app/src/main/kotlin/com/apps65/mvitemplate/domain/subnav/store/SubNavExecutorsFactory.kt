package com.apps65.mvitemplate.domain.subnav.store

import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.common.SuspendDelegationExecutor
import com.apps65.mvitemplate.domain.subnav.store.SubNavExecutorsFactory.Result
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State
import com.arkivanov.mvikotlin.core.store.Executor
import javax.inject.Inject

internal typealias SubNavExecutor = () -> Executor<Intent, Intent, State, Result, Label>

internal class SubNavExecutorsFactory @Inject constructor(
    private val dispatchersProvider: DispatchersProvider
) {
    fun create(): SubNavExecutor = {
        object : SuspendDelegationExecutor<Intent, Intent, State, Result, Label>(
            dispatchersProvider
        ) {
            override suspend fun executeIntent(intent: Intent, getState: () -> State) {
                if (intent is Intent.UpdateBSCount) {
                    dispatch(Result.UpdateBSCount(intent.count))
                }
                val label = intent.toLabel() ?: return
                publish(label)
            }
        }
    }

    sealed class Result {
        data class UpdateBSCount(val count: Int) : Result()
    }

    private fun Intent.toLabel(): Label? {
        return when (this) {
            is Intent.SwitchTo2 -> {
                Label.SwitchTo2
            }
            is Intent.SwitchTo3AndOpen -> {
                Label.SwitchTo3AndOpen
            }
            is Intent.AddTo3 -> {
                Label.AddTo3
            }
            is Intent.OpenNext -> {
                Label.OpenNext
            }
            is Intent.OpenNextGlobal -> {
                Label.OpenNextGlobal
            }
            is Intent.BackPressed -> {
                Label.BackPressed
            }
            else -> {
                null
            }
        }
    }
}
