package com.apps65.mvitemplate.domain.blank.store

import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.apps65.mvitemplate.domain.blank.store.BlankStoreFactory.Result
import com.arkivanov.mvikotlin.core.store.Reducer

internal class Reducer : Reducer<State, Result> {
    override fun State.reduce(result: Result): State {
        return if (this is State.Blank && result is Result.Increment) {
            this.copy(blankCount = this.blankCount + result.value)
        } else {
            this
        }
    }
}
