package com.apps65.mvitemplate.presentation.main

import com.apps65.mvi.BaseView

interface MainView : BaseView<MainView.Model, MainView.Event> {
    sealed class Model
    sealed class Event
}
