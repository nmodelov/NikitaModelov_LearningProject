package com.apps65.mvi.binding

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

@Deprecated(
    "dialog fragment should use viewbinding with ::inflate",
    replaceWith = ReplaceWith(
        "viewBinding(T::inflate)",
        "com.apps65.mvi.binding.viewBinding"
    ),
    level = DeprecationLevel.ERROR
)
fun <T : ViewBinding> DialogFragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun <T : ViewBinding> DialogFragment.viewBinding(
    bindingInflater: (LayoutInflater) -> T
) = DialogFragmentViewBindingDelegate(this, bindingInflater)
