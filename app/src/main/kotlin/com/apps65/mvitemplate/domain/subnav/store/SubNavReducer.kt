package com.apps65.mvitemplate.domain.subnav.store

import com.apps65.mvitemplate.domain.subnav.store.SubNavExecutorsFactory.Result
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State
import com.arkivanov.mvikotlin.core.store.Reducer

internal class SubNavReducer : Reducer<State, Result> {
    override fun State.reduce(result: Result): State {
        return when (result) {
            is Result.UpdateBSCount -> {
                copy(count = result.count)
            }
        }
    }
}
