package com.apps65.mvitemplate.presentation.subnavigation.tab

import android.content.Context
import androidx.fragment.app.Fragment
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.presentation.navigation.AppNavigator
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Пример базового класса фрагмента контейнера.
 */
@AndroidEntryPoint
abstract class TabFragment : Fragment(R.layout.fragment_tab), NavigationContainer<FlowRouter> {
    @Inject
    lateinit var flowCicerone: FlowCicerone<AppRouter>
    private val flowNavigator by lazy {
        AppNavigator(
            requireActivity(),
            R.id.fc_container,
            flowCicerone,
            requireActivity().supportFragmentManager,
            childFragmentManager
        )
    }
    private var tabListener: TabListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent is TabListener) {
            tabListener = parent
        }
    }

    override fun onResume() {
        super.onResume()
        flowCicerone.cicerone(tab().tag()).getNavigatorHolder().setNavigator(flowNavigator)
        tabListener?.tabResumed(tab())
    }

    override fun onPause() {
        flowCicerone.cicerone(tab().tag()).getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun router() = flowCicerone.router(tab().tag())

    protected abstract fun tab(): Tab
}
