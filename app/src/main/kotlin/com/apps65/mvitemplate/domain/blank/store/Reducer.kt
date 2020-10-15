package com.apps65.mvitemplate.domain.blank.store

import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory.Result
import com.arkivanov.mvikotlin.core.store.Reducer

internal class Reducer : Reducer<State, Result> {
    override fun State.reduce(result: Result): State {
        return when (result) {
            is Result.Increment -> {
                if (this is State.Blank) {
                    copy(blankCount = blankCount + result.value)
                } else {
                    this
                }
            }
            is Result.ChangeConnection -> {
                if (this is State.Blank) {
                    copy(connected = result.connected)
                } else {
                    this
                }
            }
        }
    }
}
