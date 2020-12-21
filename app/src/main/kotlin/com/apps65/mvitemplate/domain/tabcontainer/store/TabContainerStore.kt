package com.apps65.mvitemplate.domain.tabcontainer.store

import android.os.Parcelable
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Intent
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Label
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.parcelize.Parcelize

interface TabContainerStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class SwitchTab(val menuItemId: Int) : Intent()
        data class ReselectTab(val tag: String) : Intent()
    }

    sealed class State : Parcelable {
        @Parcelize
        class Blank : State()
    }

    sealed class Label {
        data class SwitchedTab(val menuItemId: Int) : Label()
        data class ReselectedTab(val tag: String) : Label()
    }
}
