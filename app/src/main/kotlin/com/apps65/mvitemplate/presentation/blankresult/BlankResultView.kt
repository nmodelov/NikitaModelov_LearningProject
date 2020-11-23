package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.BaseView

interface BlankResultView : BaseView<BlankResultView.Model, BlankResultView.Event> {
    data class Model(val blankCount: Int)

    sealed class Event
}
