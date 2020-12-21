package com.apps65.mvitemplate.presentation.main

import android.os.Bundle
import com.apps65.mvi.BaseActivity
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.saving.StateBundleKeeper
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.ActivityMainBinding
import com.apps65.mvitemplate.presentation.navigation.AppNavigator
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.NavigationContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : BaseActivity<MainView>(), NavigationContainer<AppRouter> {
    @Inject
    lateinit var flowCicerone: FlowCicerone<AppRouter>
    private val navigator: AppNavigator by lazy {
        AppNavigator(
            this,
            R.id.content,
            flowCicerone
        )
    }

    @Inject
    lateinit var binderProvider: Provider<MainBinder>

    override val binder by viewModelFrom { binderProvider }
    override val viewImpl by lazy(mode = LazyThreadSafetyMode.NONE) { MainViewImpl() }
    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var stateKeeper: StateBundleKeeper
    override val stateBundleKeeper by lazy(mode = LazyThreadSafetyMode.NONE) { stateKeeper }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        flowCicerone.mainCicerone().getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        flowCicerone.mainCicerone().getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stateBundleKeeper.saveState(outState)
    }

    override fun router(): AppRouter = flowCicerone.mainRouter()
}
