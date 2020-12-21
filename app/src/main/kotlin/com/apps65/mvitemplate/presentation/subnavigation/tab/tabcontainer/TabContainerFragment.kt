package com.apps65.mvitemplate.presentation.subnavigation.tab.tabcontainer

import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.viewFrom
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.FragmentTabsContainerBinding
import com.apps65.mvitemplate.presentation.base.BaseStateFragment
import com.apps65.mvitemplate.presentation.navigation.AppNavigator
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.apps65.mvitemplate.presentation.subnavigation.tab.Tab
import com.apps65.mvitemplate.presentation.subnavigation.tab.TabListener
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class TabContainerFragment :
    BaseStateFragment<TabContainerView>(R.layout.fragment_tabs_container),
    NavigationContainer<FlowRouter>,
    TabListener {

    @Inject
    lateinit var flowCicerone: FlowCicerone<AppRouter>

    private val binding by viewBinding(FragmentTabsContainerBinding::bind)

    private val flowNavigator by lazy {
        AppNavigator(
            requireActivity(),
            R.id.ftcContainer,
            flowCicerone,
            requireActivity().supportFragmentManager,
            childFragmentManager
        )
    }

    @Inject
    lateinit var binderProvider: Provider<TabContainerBinder>

    override val binder by viewModelFrom { binderProvider }

    override val viewImpl by viewFrom { TabContainerViewImpl(::binding) }

    override fun onResume() {
        super.onResume()
        flowCicerone.flowContainerCicerone().getNavigatorHolder().setNavigator(flowNavigator)
    }

    override fun onPause() {
        flowCicerone.flowContainerCicerone().getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun router(): FlowRouter = flowCicerone.flowContainerRouter()

    override fun tabResumed(tab: Tab) {
        with(binding.bottomNavigationView) {
            setOnNavigationItemSelectedListener(null)
            setOnNavigationItemReselectedListener(null)
            selectedItemId = tab.menuItemId()
            setOnNavigationItemSelectedListener(viewImpl)
            setOnNavigationItemReselectedListener(viewImpl)
        }
    }
}
