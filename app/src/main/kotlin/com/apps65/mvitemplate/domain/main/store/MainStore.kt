package com.apps65.mvitemplate.domain.main.store

import android.os.Parcelable
import com.apps65.mvitemplate.domain.main.store.MainStore.Intent
import com.apps65.mvitemplate.domain.main.store.MainStore.Label
import com.apps65.mvitemplate.domain.main.store.MainStore.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.parcelize.Parcelize

interface MainStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Start : Intent()
    }

    sealed class Action {
        object Blank : Action()
    }

    sealed class State : Parcelable {
        @Parcelize
        object Init : State()

        @Parcelize
        object Idle : State()
    }

    sealed class Label {
        object Started : Label()
    }
}
