package com.apps65.mvitemplate.presentation.blankresult

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun blankResultScreen(count: Int) = FragmentScreen {
    BlankResultFragment.newInstance(count)
}
