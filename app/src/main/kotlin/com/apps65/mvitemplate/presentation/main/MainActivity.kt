package com.apps65.mvitemplate.presentation.main

import android.os.Bundle
import com.apps65.mvi.BaseActivity
import com.apps65.mvi.binding.viewBinding
import com.apps65.mvi.saving.StateBundleKeeper
import com.apps65.mvi.viewModelFrom
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.databinding.ActivityMainBinding
import com.apps65.mvitemplate.presentation.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : BaseActivity<MainView>() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var binderProvider: Provider<MainBinder>

    override val binder by viewModelFrom { binderProvider }
    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var stateBundleKeeper: StateBundleKeeper

    private var navigator = AppNavigator(this, R.id.content)

    override fun onCreate(savedInstanceState: Bundle?) {
        stateBundleKeeper.restoreState(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val mainView = MainViewImpl()
        binder.onViewCreated(mainView)
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