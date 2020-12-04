package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.BaseView
import com.apps65.mvitemplate.domain.blank.store.BlankStore.DiceState

interface BlankView : BaseView<BlankView.Model, BlankView.Event> {
    data class Model(val blankCount: Int, val connection: Boolean, val diceState: DiceState?)

    sealed class Event {
        object OnBlankClick : Event()
        object OnResultClick : Event()
        object RollDice : Event()
    }
}
