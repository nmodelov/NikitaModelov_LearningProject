package com.apps65.commonui.ext

import android.content.res.Resources

/*region dp<-->px conversing*/
/**
 * Convert dp to pixels. Return result as Float
 */
val Int.dpToPxF: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

/**
 * Convert dp to pixels. Return result as Integer.
 */
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Convert px to dp.
 */
val Float.pxToDp: Float
    get() {
        val displayMetrics = Resources.getSystem().displayMetrics
        return this / (displayMetrics.densityDpi.toFloat() / displayMetrics.density)
    }
/*endregion*/
