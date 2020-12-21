package com.apps65.mvitemplate.presentation.subnavigation.tab.tabcontainer

import com.apps65.mvi.BaseView
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Intent
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.State
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

interface TabContainerView :
    BaseView<State, Intent>,
    OnNavigationItemReselectedListener,
    OnNavigationItemSelectedListener
