package com.apps65.mvitemplate.domain.blank.store

import android.os.Parcelable
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.parcelize.Parcelize

interface BlankStore : Store<Intent, State, Label> {
    sealed class Intent {
        object Increment : Intent()
        object OnResult : Intent()
        object RollDice : Intent()
        object SubNavigation : Intent()
    }

    sealed class Action {
        data class Connection(val connected: Boolean) : Action()
    }

    @Parcelize
    data class State(
        val blankCount: Int,
        val connected: Boolean = false,
        val diceState: DiceState? = null
    ) : Parcelable

    sealed class Label {
        data class Result(val count: Int) : Label()
        object Blank : Label()
        object SubNavigation : Label()
    }

    sealed class DiceState : Parcelable {
        @Parcelize
        object Spinning : DiceState()

        @Parcelize
        data class DiceIdle(val value: Int) : DiceState()
    }
}
