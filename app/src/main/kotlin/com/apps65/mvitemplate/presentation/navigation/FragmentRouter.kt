package com.apps65.mvitemplate.presentation.navigation

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentRouter(
    val clazz: KClass<out Fragment>
)
