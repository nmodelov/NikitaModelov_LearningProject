package com.apps65.mvitemplate.domain.blankresult.store

import android.os.Parcelable
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Intent
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Label
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.State
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.android.parcel.Parcelize

interface BlankResultStore : Store<Intent, State, Label> {

    sealed class Intent

    sealed class State : Parcelable {
        @Parcelize
        data class Blank(val blankCount: Int) : State()
    }

    sealed class Label
}
