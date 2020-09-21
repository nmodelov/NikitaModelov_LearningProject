package com.apps65.mvitemplate.domain.blank.store

import android.os.Parcelable
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.android.parcel.Parcelize

interface BlankStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Increment : Intent()
    }

    sealed class Action {
        object Blank : Action()
    }

    sealed class State : Parcelable {
        @Parcelize
        data class Blank(val blankCount: Int) : State()
    }

    sealed class Label {
        object Blank : Label()
    }
}
