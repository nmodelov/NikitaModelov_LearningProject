package com.apps65.mvitemplate.presentation.subnavigation

import android.os.Parcelable
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.parcelize.Parcelize

fun subNavScreen() = FragmentScreen {
    SubNavFragment.newInstance()
}

@Parcelize
data class SubNavScreenParams(
    val label: String
) : Parcelable
