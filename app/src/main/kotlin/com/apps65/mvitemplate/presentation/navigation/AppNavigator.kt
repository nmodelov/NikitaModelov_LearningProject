package com.apps65.mvitemplate.presentation.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowNavigator

class AppNavigator(
    activity: FragmentActivity,
    containerId: Int,
    flowCicerone: FlowCicerone<AppRouter>,
    activityFM: FragmentManager = activity.supportFragmentManager,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = FragmentFactory()
) : FlowNavigator<AppRouter>(activity, containerId, flowCicerone, activityFM, fragmentManager, fragmentFactory)
