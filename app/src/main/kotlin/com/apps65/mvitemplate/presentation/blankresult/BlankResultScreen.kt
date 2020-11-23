package com.apps65.mvitemplate.presentation.blankresult

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class BlankResultScreen(private val count: Int) : SupportAppScreen() {
    override fun getFragment(): Fragment = BlankResultFragment.newInstance(count)
}
