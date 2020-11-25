package com.apps65.mvitemplate.presentation.main

import android.os.Bundle
import com.apps65.mvi.BaseActivity
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.saving.StateBundleKeeper
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.ActivityMainBinding
import com.apps65.mvitemplate.presentation.navigation.AppNavigator
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : BaseActivity<MainView>() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var binderProvider: Provider<MainBinder>

    override val binder by viewModelFrom { binderProvider }
    override val viewImpl by lazy(mode = LazyThreadSafetyMode.NONE) { MainViewImpl() }
    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var stateBundleKeeper: StateBundleKeeper

    private var navigator = AppNavigator(this, R.id.content)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateBundleKeeper.restoreState(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stateBundleKeeper.saveState(outState)
    }
}
