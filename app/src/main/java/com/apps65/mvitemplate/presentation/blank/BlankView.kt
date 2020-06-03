package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.BaseView

interface BlankView : BaseView<BlankView.Model, BlankView.Event> {
    data class Model(val blankCount: Int)

    sealed class Event {
        object OnBlankClick : Event()
    }
}
