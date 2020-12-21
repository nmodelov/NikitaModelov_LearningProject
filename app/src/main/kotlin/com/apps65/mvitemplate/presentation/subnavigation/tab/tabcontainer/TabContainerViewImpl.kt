package com.apps65.mvitemplate.presentation.subnavigation.tab.tabcontainer

import android.view.MenuItem
import com.apps65.mvi.binding.BindingView
import com.apps65.mvitemplate.databinding.FragmentTabsContainerBinding
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.Intent
import com.apps65.mvitemplate.domain.tabcontainer.store.TabContainerStore.State
import com.apps65.mvitemplate.presentation.subnavigation.tab.Tab
import timber.log.Timber

class TabContainerViewImpl(val binding: () -> FragmentTabsContainerBinding) :
    BindingView<FragmentTabsContainerBinding, State, Intent>(binding), TabContainerView {

    init {
        with(binding()) {
            bottomNavigationView.itemIconTintList = null
            bottomNavigationView.setOnNavigationItemReselectedListener(this@TabContainerViewImpl)
            bottomNavigationView.setOnNavigationItemReselectedListener(this@TabContainerViewImpl)
        }
    }

    override fun render(binding: FragmentTabsContainerBinding, model: State) {
        Timber.d("render $model")
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        dispatch(Intent.ReselectTab(Tab.fromMenuItemId(item.itemId).tag()))
    }

    // переключаем вкладку при выборе BNV меню
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        with(binding().bottomNavigationView) {
            setOnNavigationItemSelectedListener(null)
            setOnNavigationItemReselectedListener(null)
            dispatch(Intent.SwitchTab(item.itemId))
            setOnNavigationItemSelectedListener(this@TabContainerViewImpl)
            setOnNavigationItemReselectedListener(this@TabContainerViewImpl)
            return true
        }
    }
}
