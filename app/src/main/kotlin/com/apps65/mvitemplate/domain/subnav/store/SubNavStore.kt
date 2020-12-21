package com.apps65.mvitemplate.domain.subnav.store

import android.os.Parcelable
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.parcelize.Parcelize

interface SubNavStore : Store<Intent, State, Label> {
    sealed class Intent {
        object BackPressed : Intent()
        object SwitchTo2 : Intent()
        object SwitchTo3AndOpen : Intent()
        object AddTo3 : Intent()
        object OpenNext : Intent()
        object OpenNextGlobal : Intent()

        data class UpdateBSCount(val count: Int) : Intent()
    }

    sealed class Label {
        object BackPressed : Label()
        object SwitchTo2 : Label()
        object SwitchTo3AndOpen : Label()
        object AddTo3 : Label()
        object OpenNext : Label()
        object OpenNextGlobal : Label()
    }

    @Parcelize
    data class State(
        val count: Int = 1
    ) : Parcelable
}
