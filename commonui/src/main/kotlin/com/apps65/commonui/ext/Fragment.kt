package com.apps65.commonui.ext

import android.app.Activity
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

/*region keyboard management*/
/**
 * Show keyboard.
 *
 * @receiver [Fragment] as context provider
 */
fun Fragment.showKeyboard() {
    (requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?)?.apply {
        toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }
}

/**
 * Hide keyboard.
 *
 * @receiver [Fragment] as context provider.
 */
fun Fragment.hideKeyboard() {
    (requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?)?.apply {
        hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
/*endregion*/

/*region fragment with parcelable initial arguments*/
private const val BUNDLE_INITIAL_ARGS = "BUNDLE_INITIAL_ARGS"

/**
 * Pass parcelable arguments to [F] fragment with [BUNDLE_INITIAL_ARGS] key and return [F] fragment.
 *
 * @receiver target [Fragment].
 */
fun <F : Fragment> F.withInitialArguments(params: Parcelable) = apply {
    arguments = bundleOf(BUNDLE_INITIAL_ARGS to params)
}

/**
 * Return parcelable argument which was be passed by [withInitialArguments] method.
 *
 * @receiver target [Fragment].
 * @param [T] Argument type.
 * @throws IllegalArgumentException when parcelable argument with key [BUNDLE_INITIAL_ARGS] not found.
 * @return Argument as [T].
 */
fun <T : Parcelable> Fragment.initialArguments(): T {
    return requireArguments().getParcelable<T>(BUNDLE_INITIAL_ARGS)
        ?: throw IllegalArgumentException("Fragment doesn't contain initial args")
}
/*endregion*/
