package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.SimpleBinder
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Intent
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.Label
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore.State
import javax.inject.Inject

class BlankResultBinder @Inject constructor(
    blankStore: BlankResultStore,
    dispatchersProvider: DispatchersProvider,
) : SimpleBinder<Intent, State, Label, BlankResultView>(blankStore, dispatchersProvider)
