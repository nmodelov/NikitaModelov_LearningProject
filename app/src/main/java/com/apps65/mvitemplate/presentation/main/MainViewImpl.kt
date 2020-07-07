package com.apps65.mvitemplate.presentation.main

import android.os.Bundle
import com.arkivanov.mvikotlin.core.view.BaseMviView

class MainViewImpl : BaseMviView<MainView.Model, MainView.Event>(), MainView {

    fun restoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            dispatch(MainView.Event.OnAppStart)
        }
    }
}
