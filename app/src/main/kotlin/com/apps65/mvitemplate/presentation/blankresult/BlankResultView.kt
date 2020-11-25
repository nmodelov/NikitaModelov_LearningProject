package com.apps65.mvitemplate.presentation.blankresult

import com.apps65.mvi.BaseView
import com.apps65.mvitemplate.domain.blankresult.store.BlankResultStore

interface BlankResultView : BaseView<BlankResultStore.State, BlankResultStore.Intent>
