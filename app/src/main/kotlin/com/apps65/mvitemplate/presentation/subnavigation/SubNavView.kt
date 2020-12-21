package com.apps65.mvitemplate.presentation.subnavigation

import com.apps65.mvi.BaseView
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State

interface SubNavView : BaseView<State, Intent>
