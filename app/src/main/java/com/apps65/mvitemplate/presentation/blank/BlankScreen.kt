package com.apps65.mvitemplate.presentation.blank

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object BlankScreen : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return BlankFragment.newInstance()
    }
}
