package com.apps65.mvitemplate.domain.main.store

import com.apps65.mvitemplate.domain.main.store.MainStore.Intent
import com.apps65.mvitemplate.domain.main.store.MainStore.Label
import com.apps65.mvitemplate.domain.main.store.MainStore.State
import com.arkivanov.mvikotlin.core.store.Store

interface MainStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Start : Intent()
    }

    sealed class State {
        object Idle : State()
        object Loading : State()
    }

    sealed class Label {
        object Started : Label()
    }
}
