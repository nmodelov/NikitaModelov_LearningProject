package com.apps65.commonui.ext

import android.view.View

/*region throttled click listener*/
private var lastClickTimestamp = 0L

/**
 * Register a callback to be invoked when this view is clicked.
 * If less than [delay] ms has been passed from last click callback will not be invoked.
 *
 * @param delay Delay in ms. 500ms by default
 * @param clickListener The callback that will run.
 */
fun View.setThrottledClickListener(delay: Long = 500L, clickListener: (View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        val delta = currentTime - lastClickTimestamp
        if (delta !in 0..delay) {
            lastClickTimestamp = currentTime
            clickListener(this)
        }
    }
}
/*endregion throttled click listener*/
