package com.apps65.mvitemplate.presentation.subnavigation.tab

import androidx.fragment.app.Fragment
import com.apps65.mvitemplate.presentation.navigation.AppRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer

fun Fragment.router(): AppRouter {
    return when {
        parentFragment is NavigationContainer<*> -> {
            (this.parentFragment as NavigationContainer<*>).router() as AppRouter
        }
        requireActivity() is NavigationContainer<*> -> {
            (requireActivity() as NavigationContainer<*>).router() as AppRouter
        }
        else -> {
            throw IllegalStateException("Can't provide router for $this $tag")
        }
    }
}
