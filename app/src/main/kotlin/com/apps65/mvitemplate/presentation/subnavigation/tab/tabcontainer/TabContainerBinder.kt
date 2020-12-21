package com.apps65.mvitemplate.presentation.subnavigation.tab.tabcontainer

import com.apps65.mvi.SimpleBinder
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Intent
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Label
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.State
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.apps65.mvitemplate.presentation.subnavigation.tab.Tab
import com.github.aradxxx.ciceroneflow.FlowCicerone
import javax.inject.Inject

class TabContainerBinder @Inject constructor(
    tabContainerStore: TabContainerStore,
    dispatchersProvider: DispatchersProvider,
    flowCicerone: FlowCicerone<AppRouter>
) : SimpleBinder<Intent, State, Label, TabContainerView>(tabContainerStore, dispatchersProvider) {
    private val router = flowCicerone.flowContainerRouter()
    override fun handleLabel(label: Label) {
        when (label) {
            is Label.SwitchedTab -> router.switch(Tab.fromMenuItemId(label.menuItemId).screen())
            is Label.ReselectedTab -> router.backTo(label.tag, null)
        }
    }
}
