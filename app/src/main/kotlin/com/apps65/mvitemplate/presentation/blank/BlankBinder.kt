package com.apps65.mvitemplate.presentation.blank

import com.apps65.mvi.DefaultBinder
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvitemplate.domain.blank.store.BlankStore
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Intent
import com.apps65.mvitemplate.domain.blank.store.BlankStore.Label
import com.apps65.mvitemplate.domain.blank.store.BlankStore.State
import com.apps65.mvitemplate.presentation.blank.BlankView.Event
import com.apps65.mvitemplate.presentation.blank.BlankView.Model
import com.apps65.mvitemplate.presentation.blankresult.blankResultScreen
import com.github.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Binder which uses Model for representing model State and Event for representing Intent
 */
class BlankBinder @Inject constructor(
    blankStore: BlankStore,
    dispatchersProvider: DispatchersProvider,
    private val router: Router,
) : DefaultBinder<Intent, State, Label, Event, Model, BlankView>(
    blankStore,
    dispatchersProvider
) {
    override fun handleLabel(label: Label) {
        when (label) {
            Label.Blank -> Timber.i("$label has been received")
            is Label.Result -> router.replaceScreen(blankResultScreen(label.count))
        }
    }

    /**
     * Optional. See [com.apps65.mvi.SimpleBinder]
     */
    override val stateToModel: suspend (State.() -> Model) = {
        Model(this.blankCount, this.connected, this.diceState)
    }

    override val eventToIntent: suspend (Event.() -> Intent) = {
        when (this) {
            Event.OnBlankClick -> Intent.Increment
            Event.OnResultClick -> Intent.OnResult
            Event.RollDice -> Intent.RollDice
        }
    }
}
