package com.apps65.mvitemplate.presentation.subnavigation

import com.apps65.mvi.SimpleBinder
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Intent
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label.AddTo3
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label.OpenNext
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label.OpenNextGlobal
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label.SwitchTo2
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.Label.SwitchTo3AndOpen
import com.apps65.mvitemplate.domain.subnav.store.SubNavStore.State
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.apps65.mvitemplate.presentation.navigation.FragmentRouter
import com.apps65.mvitemplate.presentation.subnavigation.tab.Tab.Tab2
import com.apps65.mvitemplate.presentation.subnavigation.tab.Tab.Tab3
import javax.inject.Inject

class SubNavBinder @Inject constructor(
    store: SubNavStore,
    dispatchersProvider: DispatchersProvider,
    @FragmentRouter(SubNavFragment::class)
    private val router: AppRouter
) : SimpleBinder<Intent, State, Label, SubNavView>(store, dispatchersProvider) {
    override fun handleLabel(label: Label) {
        when (label) {
            is SwitchTo2 -> {
                router.switch(Tab2.screen())
            }
            is SwitchTo3AndOpen -> {
                router.switch(Tab3.screen())
                router.navigateTo(Tab3.tag(), subNavScreen())
            }
            is AddTo3 -> {
                router.navigateTo(Tab3.tag(), subNavScreen())
            }
            is OpenNext -> {
                router.navigateTo(subNavScreen())
            }
            is OpenNextGlobal -> {
                router.navigateTo("", subNavScreen())
            }
            Label.BackPressed -> {
                router.exit()
            }
        }
    }
}
